package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

// -----------------------------
// CUSTBALANCE (inventory for customer)
// No key given — synthetic
// -----------------------------
@Entity
@Table(name = "CUSTBALANCE")
public class CustBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "PARTDES", length = 59)
    private String partDes;

    @Column(name = "TBALANCE", precision = 16, scale = 2)
    private BigDecimal tBalance;

    @Column(name = "TUNITNAME", length = 3)
    private String tUnitName;

    @Column(name = "SERIALNAME", length = 22)
    private String serialName;

    @Column(name = "EXPIRYDATE")
    private OffsetDateTime expiryDate; // Date

    @Column(name = "ACTNAME", length = 16)
    private String actName;

    @Column(name = "BALANCE", precision = 17, scale = 2)
    private BigDecimal balance;

    @Column(name = "UNITNAME", length = 3)
    private String unitName;

    @Column(name = "LASTDATE")
    private OffsetDateTime lastDate; // Date

    @Column(name = "ACT")
    private Long act;

    @Column(name = "PART")
    private Long part;

    @Column(name = "SERIAL")
    private Long serial;

    @Column(name = "WARHS")
    private Long warhs;

    // Getters/setters…
}
