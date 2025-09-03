package odata.demo.bootstrap;

import odata.demo.entity.CarMaker;
import odata.demo.entity.CarModel;
import odata.demo.repo.CarMakerRepository;
import odata.demo.repo.CarModelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DemoDataLoaderCars implements CommandLineRunner {

    private final CarMakerRepository makers;
    private final CarModelRepository models;

    public DemoDataLoaderCars(CarMakerRepository makers, CarModelRepository models) {
        this.makers = makers;
        this.models = models;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // чтобы не дублировать при каждом старте
        if (makers.count() > 0 || models.count() > 0) return;

        // бренд -> список корректных моделей
        Map<String, List<String>> catalog = new LinkedHashMap<>();
        catalog.put("Toyota",       List.of("Corolla", "Camry", "RAV4", "Yaris", "Prius"));
        catalog.put("Honda",        List.of("Civic", "Accord", "CR-V", "Fit", "HR-V"));
        catalog.put("Ford",         List.of("Focus", "Mustang", "Explorer", "Fiesta", "F-150"));
        catalog.put("BMW",          List.of("1 Series", "3 Series", "5 Series", "X3", "X5"));
        catalog.put("Mercedes",     List.of("A-Class", "C-Class", "E-Class", "GLC", "GLE"));
        catalog.put("Audi",         List.of("A3", "A4", "A6", "Q5", "Q7"));
        catalog.put("Volkswagen",   List.of("Polo", "Golf", "Passat", "Tiguan", "Touareg"));
        catalog.put("Hyundai",      List.of("i30", "Elantra", "Tucson", "Sonata", "Santa Fe"));
        catalog.put("Kia",          List.of("Rio", "Ceed", "Sportage", "Sorento", "Soul"));
        catalog.put("Nissan",       List.of("Micra", "Sentra", "Altima", "Qashqai", "X-Trail"));

        // создаём производителей
        Map<String, CarMaker> makerEntities = catalog.keySet().stream()
                .map(name -> {
                    CarMaker m = new CarMaker();
                    m.setName(name);
                    return makers.save(m);
                })
                .collect(Collectors.toMap(CarMaker::getName, m -> m, (a, b) -> a, LinkedHashMap::new));

        // создаём 50 моделей: по 5 на каждого производителя (10 * 5 = 50)
        final int total = 50;
        final int perMaker = total / catalog.size(); // 5
        Random rnd = new Random(42);

        int created = 0;
        for (var entry : catalog.entrySet()) {
            String brand = entry.getKey();
            CarMaker maker = makerEntities.get(brand);
            List<String> modelPool = entry.getValue();

            for (int i = 0; i < perMaker && created < total; i++) {
                String modelName = modelPool.get(rnd.nextInt(modelPool.size()));

                CarModel cm = new CarModel();
                cm.setName(modelName);
                cm.setYear(2010 + rnd.nextInt(16)); // 2010..2025
                cm.setSku(buildSku(brand, modelName, cm.getYear(), rnd));
                cm.setMaker(maker);

                models.save(cm);
                created++;
            }
        }

        // если вдруг total не кратно числу брендов — докидаем случайные, сохраняя соответствие
        List<String> brands = new ArrayList<>(catalog.keySet());
        while (created < total) {
            String brand = brands.get(rnd.nextInt(brands.size()));
            CarMaker maker = makerEntities.get(brand);
            List<String> pool = catalog.get(brand);

            String modelName = pool.get(rnd.nextInt(pool.size()));
            int year = 2010 + rnd.nextInt(16);

            CarModel cm = new CarModel();
            cm.setName(modelName);
            cm.setYear(year);
            cm.setSku(buildSku(brand, modelName, year, rnd));
            cm.setMaker(maker);

            models.save(cm);
            created++;
        }
    }

    private static String buildSku(String brand, String model, int year, Random rnd) {
        String b = brand.replaceAll("[^A-Za-z0-9]", "").toUpperCase(Locale.ROOT);
        String m = model.replaceAll("[^A-Za-z0-9]", "").toUpperCase(Locale.ROOT);
        String yy = String.valueOf(year);
        String tail = Integer.toHexString(rnd.nextInt(0xFFFF)).toUpperCase(Locale.ROOT);
        return slice(b, 3) + "-" + slice(m, 4) + "-" + yy + "-" + tail;
    }

    private static String slice(String s, int n) {
        return s.substring(0, Math.min(n, s.length()));
    }
}
