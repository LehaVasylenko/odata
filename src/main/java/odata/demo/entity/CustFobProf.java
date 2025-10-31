package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTFOBPROF")
public class CustFobProf {
    @Id
    @Column(name = "PROFNAME", length = 16)
    private String profName;

    @Column(name = "CURDATE")
    private OffsetDateTime curDate;
    @Column(name = "NAME", length = 37)
    private String contactName;
    @Column(name = "SUPNAME", length = 16)
    private String supName;
    @Column(name = "SUPDES", length = 48)
    private String supDes;
    @Column(name = "SUPCONTACTNAME", length = 37)
    private String supContactName;
    @Column(name = "REFERENCE", length = 12)
    private String reference;
    @Column(name = "EXPIRYDATE")
    private OffsetDateTime expiryDate;
    @Column(name = "QPRICE", precision = 16, scale = 2)
    private BigDecimal qprice;
    @Column(name = "CODE", length = 3)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
