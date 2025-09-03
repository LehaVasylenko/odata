package odata.demo.bootstrap;

import odata.demo.entity.*;
import odata.demo.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DemoDataLoaderOrders implements CommandLineRunner {

    private final CustomerRepository customers;
    private final ProductRepository products;
    private final WarehouseRepository warehouses;
    private final BatchRepository batches;
    private final LocationRepository locations;
    private final SalesOrderRepository orders;
    private final SalesOrderLineRepository lines;
    private final LineAllocationRepository allocations;
    private final AllocationSerialRepository serials;

    public DemoDataLoaderOrders(CustomerRepository customers,
                                ProductRepository products,
                                WarehouseRepository warehouses,
                                BatchRepository batches,
                                LocationRepository locations,
                                SalesOrderRepository orders,
                                SalesOrderLineRepository lines,
                                LineAllocationRepository allocations,
                                AllocationSerialRepository serials) {
        this.customers = customers;
        this.products = products;
        this.warehouses = warehouses;
        this.batches = batches;
        this.locations = locations;
        this.orders = orders;
        this.lines = lines;
        this.allocations = allocations;
        this.serials = serials;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // чтобы при рестарте не плодить дубликаты
        if (orders.count() > 0) return;

        Random rnd = new Random(42);

        // === справочники ===
        var savedCustomers = seedCustomers();
        var savedProducts  = seedProducts();
        var savedWhs       = seedWarehouses();
        var savedBatches   = seedBatches(rnd);
        var savedLocs      = seedLocations();

        // === заказы ===
        int orderCount = 12;
        AtomicInteger orderSeq = new AtomicInteger(1000);

        for (int i = 0; i < orderCount; i++) {
            SalesOrder so = new SalesOrder();
            so.setOrderNo("SO-" + orderSeq.incrementAndGet());
            so.setOrderDate(toDate(LocalDate.now().minusDays(rnd.nextInt(60))));
            so.setCustomer(pick(savedCustomers, rnd));
            so = orders.save(so);

            // 2..5 строк
            int lineCount = 2 + rnd.nextInt(4);
            for (int ln = 1; ln <= lineCount; ln++) {
                SalesOrderLine line = new SalesOrderLine();
                line.setOrder(so);
                line.setLineNo(ln);
                line.setProduct(pick(savedProducts, rnd));
                line.setWarehouse(pick(savedWhs, rnd));

                BigDecimal qty = bd(1 + rnd.nextInt(20), 0)
                        .add(bd(rnd.nextInt(1000), 3)); // до тысячных
                BigDecimal price = bd(10 + rnd.nextInt(490), 2);
                line.setQuantity(qty.setScale(3, RoundingMode.HALF_UP));
                line.setUnitPrice(price.setScale(2, RoundingMode.HALF_UP));

                line = lines.save(line);

                // 1..3 разреза; гарантируем суммарно <= qty (реалистичнее)
                int allocCount = 1 + rnd.nextInt(3);
                BigDecimal remaining = line.getQuantity();

                for (int a = 1; a <= allocCount; a++) {
                    LineAllocation la = new LineAllocation();
                    la.setLine(line);
                    la.setBatch(pick(savedBatches, rnd));
                    la.setLocation(pick(savedLocs, rnd));

                    BigDecimal maxForThis = (a == allocCount) ? remaining
                            : remaining.multiply(bd(0.6, 1 + rnd.nextInt(2))); // часть остатка
                    BigDecimal allocQty = maxForThis.max(bd(0.001, 3))
                            .multiply(bd(0.5 + rnd.nextDouble() * 0.5, 3))
                            .setScale(3, RoundingMode.HALF_UP);

                    if (allocQty.compareTo(remaining) > 0) allocQty = remaining;
                    if (allocQty.compareTo(bd(0, 3)) <= 0) allocQty = bd(0.001, 3);

                    la.setAllocatedQty(allocQty);
                    la = allocations.save(la);
                    remaining = remaining.subtract(allocQty).max(bd(0, 3));

                    // 1..4 серийника на разрез (для регресса сериализованных сценариев)
                    int serialCount = 1 + rnd.nextInt(4);
                    for (int s = 0; s < serialCount; s++) {
                        AllocationSerial as = new AllocationSerial();
                        as.setAllocation(la);
                        as.setSerialNo(buildSerial(line.getOrder().getOrderNo(), line.getLineNo(), la.getId(), s));
                        as.setConditionCode(pick(List.of("NEW", "USED", "DEFECT"), rnd));
                        serials.save(as);
                    }
                }
            }
        }
    }

    // ---------- helpers ----------

    private List<Customer> seedCustomers() {
        var names = List.of(
                "Acme Corp", "Globex", "Initech", "Umbrella Co", "Stark Industries",
                "Wayne Enterprises", "Soylent", "Wonka LLC", "Cyberdyne", "Tyrell Corp"
        );
        var emails = names.stream()
                .map(n -> n.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", ".") + "@example.com")
                .toList();

        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Customer c = new Customer();
            c.setName(names.get(i));
            c.setEmail(emails.get(i));
            list.add(c);
        }
        return customers.saveAll(list);
    }

    private List<Product> seedProducts() {
        var pool = List.of(
                "Widget A","Widget B","Widget C","Gadget X","Gadget Y","Gadget Z",
                "Bolt M8","Bolt M10","Nut M8","Nut M10","Bearing 6204","Bearing 6205",
                "Motor 1kW","Motor 2kW","Controller S","Controller M","Sensor T","Sensor H",
                "Display 7in","Display 10in"
        );
        AtomicInteger skuSeq = new AtomicInteger(1000);
        List<Product> list = new ArrayList<>();
        for (String name : pool) {
            Product p = new Product();
            p.setName(name);
            p.setSku("SKU-" + skuSeq.incrementAndGet());
            list.add(p);
        }
        return products.saveAll(list);
    }

    private List<Warehouse> seedWarehouses() {
        var defs = List.of(
                new String[]{"WHS-001", "Central Warehouse"},
                new String[]{"WHS-002", "North Hub"},
                new String[]{"WHS-003", "South Hub"}
        );
        return defs.stream().map(a -> {
            Warehouse w = new Warehouse();
            w.setCode(a[0]);
            w.setName(a[1]);
            return w;
        }).map(warehouses::save).collect(Collectors.toList());
    }

    private List<Batch> seedBatches(Random rnd) {
        // 10 партий, половина с датой истечения в будущем, часть без даты
        return IntStream.range(0, 10).mapToObj(i -> {
            Batch b = new Batch();
            b.setCode("BATCH-" + (2024 + (i % 3)) + "-" + String.format("%03d", i + 1));
            if (i % 2 == 0) {
                b.setExpiryDate(toDate(LocalDate.now().plusDays(30 + rnd.nextInt(365))));
            }
            return b;
        }).map(batches::save).collect(Collectors.toList());
    }

    private List<Location> seedLocations() {
        // 12 ячеек A01..A06, B01..B06
        List<Location> list = new ArrayList<>();
        for (char zone : new char[]{'A','B'}) {
            for (int i = 1; i <= 6; i++) {
                Location l = new Location();
                l.setCode(zone + String.format("-%02d", i));
                l.setDescription("Zone " + zone + ", slot " + i);
                list.add(l);
            }
        }
        return locations.saveAll(list);
    }

    private static <T> T pick(List<T> list, Random rnd) {
        return list.get(rnd.nextInt(list.size()));
    }

    private static BigDecimal bd(double v, int scale) {
        return BigDecimal.valueOf(v).setScale(scale, RoundingMode.HALF_UP);
    }

    private static BigDecimal bd(int v, int scale) {
        return BigDecimal.valueOf(v).setScale(scale, RoundingMode.HALF_UP);
    }

    private static Date toDate(LocalDate ld) {
        return Date.valueOf(ld);
    }

    private static String buildSerial(String orderNo, int lineNo, Long allocId, int idx) {
        return String.format("%s-%02d-%03d-%s",
                orderNo, lineNo, allocId == null ? 0 : allocId, Integer.toHexString(idx).toUpperCase());
    }
}

