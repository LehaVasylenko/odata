package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTPARTDISC")
public class CustPartDisc {
    @EmbeddedId
    private CustPartDiscId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "PARTNAME", length = 22)
    private String partName;

    @Column(name = "PARTDES", length = 59)
    private String partDes;

    @Column(name = "DISCCODE", length = 8)
    private String discCode;

    @Column(name = "DISCDES", length = 32)
    private String discDes;

    @Column(name = "PERCENT", precision = 9, scale = 2)
    private BigDecimal percent;

    @Column(name = "FROMDATE")
    private OffsetDateTime fromDate; // Date

    @Column(name = "EXPIRYDATE")
    private OffsetDateTime expiryDate; // Date

    @Column(name = "USERLOGIN", length = 20)
    private String userLogin;

    @Column(name = "UDATE")
    private OffsetDateTime udate; // DateTime

    // Getters/setters…
}
