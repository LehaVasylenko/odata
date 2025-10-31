package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTPARTPRICE")
public class CustPartPrice {
    @EmbeddedId
    private CustPartPriceId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "PARTNAME", length = 22)
    private String partName;

    @Column(name = "PARTDES", length = 59)
    private String partDes;

    @Column(name = "UNITNAME", length = 3)
    private String unitName;

    @Column(name = "EXPIRYDATE")
    private OffsetDateTime expiryDate; // Date

    @Column(name = "PRICE", precision = 13, scale = 2)
    private BigDecimal price;

    @Column(name = "VPRICE", precision = 13, scale = 2)
    private BigDecimal vprice;

    @Column(name = "CODE", length = 3)
    private String code;

    @Column(name = "PRSOURCENAME", length = 16)
    private String prSourceName;

    @Column(name = "PURTAXPERCENT", precision = 7, scale = 2)
    private BigDecimal purTaxPercent;

    @Column(name = "BASEPRICE", precision = 13, scale = 2)
    private BigDecimal basePrice;

    // Getters/setters…
}