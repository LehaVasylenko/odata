package odata.demo.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

// -----------------------------
// CUSTTREATMENTS (event log / journal)
// Key: TREATMENT
// -----------------------------
@Entity
@Table(name = "CUSTTREATMENTS")
public class CustTreatments {
    @Id
    @Column(name = "TREATMENT")
    private Long treatment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "TREATMENTCODE", length = 4)
    private String treatmentCode;

    @Column(name = "TREATMENTDES", length = 30)
    private String treatmentDes;

    @Column(name = "TRDATE")
    private OffsetDateTime trDate; // Date

    @Column(name = "AAAA_REMARKS", length = 42)
    private String remarks;

    @Column(name = "NAME", length = 37)
    private String contactName;

    // Getters/setters…
}

