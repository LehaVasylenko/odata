package odata.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTFIRSTQUEST")
public class CustFirstQuest {
    @Id
    @Column(name = "SUBJECT")
    private Long subject;

    @Column(name = "SUBJECTCODE", length = 3)
    private String subjectCode;
    @Column(name = "SUBJECTDES", length = 30)
    private String subjectDes;
    @Column(name = "BDOMAINDES", length = 48)
    private String bdomainDes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
