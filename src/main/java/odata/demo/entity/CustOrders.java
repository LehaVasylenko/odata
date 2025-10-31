package odata.demo.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

// -----------------------------
// CUSTORDERS (open orders)
// Key: ORDNAME
// -----------------------------
@Entity
@Table(name = "CUSTORDERS")
public class CustOrders {
    @Id
    @Column(name = "ORDNAME", length = 16)
    private String ordName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "ORDSTATUSDES", length = 12)
    private String ordStatusDes;

    @Column(name = "NAME", length = 37)
    private String contactName;

    @Column(name = "REFERENCE", length = 25)
    private String reference;

    @Column(name = "CURDATE")
    private OffsetDateTime curDate; // Date

    // Getters/setters…
}

