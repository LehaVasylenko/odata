package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTCPROF")
public class CustCProf {
    @Id
    @Column(name = "CPROFNUM", length = 16)
    private String cprofNum;

    @Column(name = "PDATE")
    private OffsetDateTime pdate;
    @Column(name = "NAME", length = 37)
    private String contactName;
    @Column(name = "REFERENCE", length = 12)
    private String reference;
    @Column(name = "EXPIRYDATE")
    private OffsetDateTime expiryDate;
    @Column(name = "PERCENT", precision = 8, scale = 2)
    private BigDecimal percent;
    @Column(name = "DISPRICE", precision = 16, scale = 2)
    private BigDecimal disPrice;
    @Column(name = "VAT", precision = 16, scale = 2)
    private BigDecimal vat;
    @Column(name = "TOTPRICE", precision = 16, scale = 2)
    private BigDecimal totPrice;
    @Column(name = "CODE", length = 3)
    private String currency;
    @Column(name = "STATDES", length = 24)
    private String statusDes;
    @Column(name = "USERLOGIN", length = 20)
    private String userLogin;
    @Column(name = "LCODE", length = 3)
    private String lcode;
    @Column(name = "LEXCH", precision = 13, scale = 6)
    private BigDecimal lexch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
