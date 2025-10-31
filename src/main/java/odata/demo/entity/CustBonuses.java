package odata.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTBONUSES")
public class CustBonuses {
    @EmbeddedId
    private CustBonusesId id; // (PART, LINE)


    @Column(name = "PARTNAME", length = 22)
    private String partName;
    @Column(name = "PARTDES", length = 59)
    private String partDes;
    @Column(name = "FROMDATE")
    private OffsetDateTime fromDate;
    @Column(name = "TODATE")
    private OffsetDateTime toDate;
    @Column(name = "MINQUANT", precision = 17, scale = 2)
    private BigDecimal minQuant;
    @Column(name = "BONUSPARTNAME", length = 22)
    private String bonusPartName;
    @Column(name = "BONUSPARTDES", length = 59)
    private String bonusPartDes;
    @Column(name = "BONUSQUANT", precision = 17, scale = 2)
    private BigDecimal bonusQuant;
    @Column(name = "BONUSPRICE", precision = 13, scale = 2)
    private BigDecimal bonusPrice;
    @Column(name = "BONUSCURRENCYCODE", length = 3)
    private String bonusCurrencyCode;
    @Column(name = "FAMILY")
    private Long family;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
