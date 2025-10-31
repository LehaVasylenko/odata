#!/usr/bin/env bash
set -Eeuo pipefail

### === Настройки ===
APP_NAME="odata"
JAR_PATH="target/demo-0.0.1-SNAPSHOT.jar"

LOG_DIR="logs"
RUN_DIR="run"
LOG_FILE="${LOG_DIR}/${APP_NAME}.out"
PID_FILE="${RUN_DIR}/${APP_NAME}.pid"

# runtime JVM (приложение)
JAVA_OPTS="-XX:+UseStringDeduplication -Xms256m -Xmx512m"

# можно передать снаружи:  OUTBOX_DIR=/data/odata ./deploy.sh
OUTBOX_DIR="${OUTBOX_DIR:-./db}"

### === Утилиты ===
here() { cd -- "$(dirname -- "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd -P; }
have() { command -v "$1" >/dev/null 2>&1; }
abspath() {
  local p="$1"
  if have realpath; then realpath -m "$p" 2>/dev/null || realpath "$p" 2>/dev/null || echo "$p"
  elif have readlink; then readlink -f "$p" 2>/dev/null || echo "$p"
  else echo "$p"; fi
}

### === Стартовать в корне репозитория ===
cd "$(here)"
if [[ ! -d .git ]]; then
  echo "[git] Запускай скрипт из корня репозитория (здесь нет .git)" >&2
  exit 1
fi

### === Функции ===
start_ssh_agent_if_needed() {
  # если уже есть агент (в т.ч. форвардинг) — не трогаем
  if [[ -n "${SSH_AUTH_SOCK:-}" && -S "${SSH_AUTH_SOCK:-/dev/null}" ]]; then
    if ssh-add -l >/dev/null 2>&1; then
      echo "[ssh] agent OK (keys loaded)"
    else
      echo "[ssh] agent detected but no keys. If repo uses SSH, run: ssh-add ~/.ssh/id_ed25519" >&2
    fi
    return
  fi

  echo "[ssh] no agent detected, starting ssh-agent..."
  eval "$(ssh-agent -s)"
  # при необходимости раскомментируй автоматическую подгрузку ключей:
  # [[ -f "$HOME/.ssh/id_ed25519" ]] && ssh-add "$HOME/.ssh/id_ed25519" || true
  # [[ -f "$HOME/.ssh/id_rsa"     ]] && ssh-add "$HOME/.ssh/id_rsa"     || true
}

git_pull() {
  # определить дефолтную ветку origin (HEAD)
  local default_branch
  default_branch="$(git remote show origin | sed -n 's/.*HEAD branch: //p')"
  default_branch="${default_branch:-main}"

  echo "[git] fetching origin ${default_branch}..."
  git fetch --prune origin "${default_branch}"

  echo "[git] checkout ${default_branch}..."
  git checkout -q "${default_branch}"

  echo "[git] pull --ff-only..."
  git pull --ff-only origin "${default_branch}"
}

stop_app() {
  mkdir -p "$RUN_DIR"
  local JAR_BASENAME; JAR_BASENAME="$(basename -- "$JAR_PATH")"

  if [[ -f "$PID_FILE" ]]; then
    local PID
    PID="$(cat "$PID_FILE" || true)"
    if [[ -n "${PID:-}" && "$PID" =~ ^[0-9]+$ ]] && ps -p "$PID" >/dev/null 2>&1; then
      echo "[app] stopping (pid=${PID})..."
      kill -TERM "$PID" || true

      # ждём до 10 секунд пока процесс завершится
      for ((i=0; i<10; i++)); do
        ps -p "$PID" >/dev/null 2>&1 || break
        sleep 1
      done

      if ps -p "$PID" >/dev/null 2>&1; then
        echo "[app] still running, kill -9 ${PID}"
        kill -KILL "$PID" || true
      fi
    fi
    rm -f "$PID_FILE"
  else
    # фоллбэк: по имени jar
    local PIDS
    PIDS="$(pgrep -f -- "$JAR_BASENAME" || true)"
    if [[ -n "$PIDS" ]]; then
      echo "[app] stopping by pgrep: $PIDS"
      kill -TERM $PIDS || true
    fi
  fi
}


rotate_log_if_big() {
  mkdir -p "$LOG_DIR"
  if [[ -f "$LOG_FILE" ]]; then
    local size
    size=$(wc -c < "$LOG_FILE" || echo 0)
    if (( size > 5 * 1024 * 1024 )); then
      mv -f "$LOG_FILE" "${LOG_FILE%.*}.$(date +%Y%m%d-%H%M%S).out"
      echo "[log] rotated ${LOG_FILE}"
    fi
  fi
}

build_app() {
  echo "[maven] build package -DskipTests"
  export MAVEN_OPTS="${MAVEN_OPTS:-"-Xmx512m -XX:+UseStringDeduplication -Djava.awt.headless=true"}"
  if [[ -x ./mvnw ]]; then
    ./mvnw -B -U clean package -DskipTests
  else
    mvn    -B -U clean package -DskipTests
  fi
}

start_app() {
  set -euo pipefail

  # Имя сервиса и пользователь (можешь экспортировать заранее)
  local APP_NAME="${APP_NAME:-myapp}"
  local RUN_USER="${RUN_USER:-$USER}"

  local APP_DIR="/opt/${APP_NAME}"
  local ENV_DIR="/etc/${APP_NAME}"
  local ENV_FILE="${ENV_DIR}/env"
  local SERVICE_FILE="/etc/systemd/system/${APP_NAME}.service"

  # Где взять JAR: если переменная не задана — берём последний из target/
  if [[ -z "${JAR_PATH:-}" ]]; then
    JAR_PATH="$(ls -1t target/*-runner.jar target/*-all.jar target/*with-dependencies*.jar target/*.jar 2>/dev/null | head -n1 || true)"
  fi
  if [[ -z "${JAR_PATH:-}" || ! -f "$JAR_PATH" ]]; then
    echo "ERROR: не найден jar в target/ (собери проект: mvn -DskipTests package)"; return 1
  fi

  local JAVA_BIN
  JAVA_BIN="$(command -v java || true)"
  [[ -n "$JAVA_BIN" ]] || { echo "ERROR: java не найдена в PATH"; return 1; }

  # Директории и деплой JAR
  sudo install -d -m0755 "$APP_DIR" "$ENV_DIR" "/var/log/${APP_NAME}"
  sudo cp -f "$JAR_PATH" "${APP_DIR}/app.jar"
  sudo chown -R "$RUN_USER":"$RUN_USER" "$APP_DIR" "/var/log/${APP_NAME}"
  sudo chmod 0755 "${APP_DIR}/app.jar"

  # Те же опции, что ты сейчас передаёшь в ручном запуске
  sudo tee "$ENV_FILE" >/dev/null <<EOF
JAVA_OPTS="${JAVA_OPTS:-}"
APP_OPTS="${APP_OPTS:-}"
EOF
  sudo chmod 0644 "$ENV_FILE"

  # systemd unit (минимальный)
  sudo tee "$SERVICE_FILE" >/dev/null <<EOF
[Unit]
Description=${APP_NAME} (Java/Quarkus)
After=network-online.target
Wants=network-online.target

[Service]
Type=simple
User=${RUN_USER}
WorkingDirectory=${APP_DIR}
EnvironmentFile=${ENV_FILE}
ExecStart=${JAVA_BIN} \$JAVA_OPTS -jar ${APP_DIR}/app.jar \$APP_OPTS
Restart=always
RestartSec=3
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
EOF

  # Перезагрузка unit’ов, автозапуск и старт
  sudo systemctl daemon-reload
  sudo systemctl enable --now "${APP_NAME}.service"

  echo "[OK] ${APP_NAME} запущен через systemd"
  echo "Статус:  sudo systemctl status ${APP_NAME}"
  echo "Логи:    journalctl -u ${APP_NAME} -f"
  echo "Опции:   sudo nano ${ENV_FILE}  &&  sudo systemctl restart ${APP_NAME}"
}


### === Выполнение ===
start_ssh_agent_if_needed
git_pull
stop_app
build_app
start_app

echo
echo "[done] tail -f '$LOG_FILE'    # посмотреть логи"
