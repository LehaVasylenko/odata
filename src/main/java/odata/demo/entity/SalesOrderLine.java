package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sales_order_line")
public class SalesOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="order_fk")
    private SalesOrder order;

    @Column(name="line_no", nullable=false)
    private Integer lineNo;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="product_fk")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="warehouse_fk")
    private Warehouse warehouse;

    @Column(name="qty", nullable=false, precision=18, scale=3)
    private BigDecimal quantity;

    @Column(name="unit_price", nullable=false, precision=18, scale=2)
    private BigDecimal unitPrice;

    @OneToMany(mappedBy="line", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<LineAllocation> allocations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesOrder getOrder() {
        return order;
    }

    public void setOrder(SalesOrder order) {
        this.order = order;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<LineAllocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<LineAllocation> allocations) {
        this.allocations = allocations;
    }
}
