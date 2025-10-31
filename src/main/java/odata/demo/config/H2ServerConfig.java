package odata.demo.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2ServerConfig {

    // TCP server на 9092 для подключения DBeaver
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",   // можно убрать, если подключаешься только с localhost
                "-tcpPort", "9092"
        );
    }

    // Не обязательно, но удобно если любишь веб-консоль H2
    // http://localhost:8082/ (логин/пароль как в JDBC)
    // @Bean(initMethod = "start", destroyMethod = "stop")
    // public Server h2WebServer() throws SQLException {
    //   return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
    // }
}

