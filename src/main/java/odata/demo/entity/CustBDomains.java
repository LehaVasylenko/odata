package odata.demo.entity;

import javax.persistence.*;

// -----------------------------
// CUSTBDOMAINS (business classifications)
// Key: BDOMAIN
// -----------------------------
@Entity
@Table(name = "CUSTBDOMAINS")
public class CustBDomains {
    @Id
    @Column(name = "BDOMAIN")
    private Long bdomain;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;

    @Column(name = "BDOMAINCODE", length = 6)
    private String bdomainCode;

    @Column(name = "BDOMAINDES", length = 48)
    private String bdomainDes;

    // Getters/setters…
}
