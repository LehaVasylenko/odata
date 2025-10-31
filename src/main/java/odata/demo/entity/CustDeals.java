package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTDEALS")
public class CustDeals {
    @Id
    @Column(name = "ORDNAME", length = 16)
    private String ordName;


    @Column(name = "CURDATE")
    private OffsetDateTime curDate;
    @Column(name = "DUEDATE")
    private OffsetDateTime dueDate;
    @Column(name = "QPRICE", precision = 16, scale = 2)
    private BigDecimal qprice;
    @Column(name = "PERCENT", precision = 8, scale = 2)
    private BigDecimal percent;
    @Column(name = "DISPRICE", precision = 16, scale = 2)
    private BigDecimal disPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
