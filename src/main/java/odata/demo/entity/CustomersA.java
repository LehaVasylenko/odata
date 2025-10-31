package odata.demo.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

// -----------------------------
// CUSTOMERSA (single contained subform)
// -----------------------------
@Entity
@Table(name = "CUSTOMERSA")
public class CustomersA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // synthetic key — metadata doesn't provide a key

    @OneToOne(optional = false)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer; // join via numeric CUST

    @Column(name = "HOSTNAME", length = 48)
    private String hostName;

    @Column(name = "MAILINTERFACE", length = 1)
    private String mailInterface;

    @Column(name = "USERLOGIN", length = 20)
    private String userLogin;

    @Column(name = "PAYCUSTNAME", length = 8)
    private String payCustName;

    @Column(name = "ADDRESSA", length = 80)
    private String addressA;

    @Column(name = "STATEA", length = 40)
    private String stateA;

    @Column(name = "MYNUM", length = 16)
    private String myNum;

    @Column(name = "MSVNUM", length = 9)
    private String msvNum;

    @Column(name = "USERLOGIN2", length = 20)
    private String userLogin2;

    @Column(name = "UDATE")
    private OffsetDateTime udate; // DateTime

    @Column(name = "REMARKS", length = 80)
    private String remarks;

    @Column(name = "EXTFILENAME", length = 80)
    private String extFileName;

    // Getters/setters…
}
