package odata.demo.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

// -----------------------------
// CUSTPLIST (price lists per customer)
// Key: PLIST
// -----------------------------
@Entity
@Table(name = "CUSTPLIST")
public class CustPlist {
    @Id
    @Column(name = "PLIST")
    private Long plist; // Edm.Int64

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "PLNAME", length = 6)
    private String plName;

    @Column(name = "PLDES", length = 16)
    private String plDes;

    @Column(name = "ORD")
    private Long ord;

    @Column(name = "PLDATE")
    private OffsetDateTime plDate; // Date

    // Getters/setters…
}
