package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTDISCOUNT")
public class CustDiscount {
    @Id
    @Column(name = "DISCOUNT")
    private Long discount;

    @Column(name = "DISCCODE", length = 8)
    private String discCode;
    @Column(name = "DISCDES", length = 32)
    private String discDes;
    @Column(name = "PERCENT", precision = 9, scale = 2)
    private BigDecimal percent;
    @Column(name = "FROMDATE")
    private OffsetDateTime fromDate;
    @Column(name = "EXPIRYDATE")
    private OffsetDateTime expiryDate;
    @Column(name = "USERLOGIN", length = 20)
    private String userLogin;
    @Column(name = "UDATE")
    private OffsetDateTime udate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
