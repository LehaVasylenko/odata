package odata.demo.entity;

import javax.persistence.*;

// -----------------------------
// CUSTOMERSTEXT (single contained subform)
// -----------------------------
@Entity
@Table(name = "CUSTOMERSTEXT")
public class CustomersText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // synthetic key

    @OneToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "APPEND")
    private Boolean append;

    @Column(name = "SIGNATURE")
    private Boolean signature;

    // Getters/setters…
}
