package odata.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales_order")
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_no", nullable=false, unique=true)
    private String orderNo;

    @Temporal(TemporalType.DATE)
    @Column(name="order_date", nullable=false)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="customer_fk")
    private Customer customer;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name="line_idx") // сохраняем порядок строк
    private List<SalesOrderLine> lines = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SalesOrderLine> getLines() {
        return lines;
    }

    public void setLines(List<SalesOrderLine> lines) {
        this.lines = lines;
    }
}
