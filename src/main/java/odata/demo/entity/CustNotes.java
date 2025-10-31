package odata.demo.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

// -----------------------------
// CUSTNOTES (tasks for customer)
// Key: CUSTNOTE
// -----------------------------
@Entity
@Table(name = "CUSTNOTES")
public class CustNotes {
    @Id
    @Column(name = "CUSTNOTE")
    private Long custNote;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "SUBJECT", length = 52)
    private String subject;

    @Column(name = "CURDATE")
    private OffsetDateTime curDate; // Date

    @Column(name = "DAY", length = 3)
    private String day;

    @Column(name = "USERLOGIN", length = 20)
    private String userLogin;

    @Column(name = "AUJF_ACTIVITYCODE", length = 10)
    private String activityCode;

    // Getters/setters…
}
