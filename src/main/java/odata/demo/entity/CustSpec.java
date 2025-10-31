package odata.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTSPEC")
public class CustSpec {
    @Id
    @Column(name = "CUST")
    private Long cust;

    @Column(name = "SPEC1", length = 32)
    private String spec1;
    @Column(name = "SPEC2", length = 32)
    private String spec2;
    @Column(name = "SPEC3", length = 32)
    private String spec3;
    @Column(name = "SPEC4", length = 32)
    private String spec4;
    @Column(name = "SPEC5", length = 32)
    private String spec5;
    @Column(name = "SPEC6", length = 32)
    private String spec6;
    @Column(name = "SPEC7", length = 32)
    private String spec7;
    @Column(name = "SPEC8", length = 32)
    private String spec8;
    @Column(name = "SPEC9", length = 32)
    private String spec9;
    @Column(name = "SPEC10", length = 32)
    private String spec10;
    @Column(name = "SPEC11", length = 32)
    private String spec11;
    @Column(name = "SPEC12", length = 32)
    private String spec12;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST", insertable = false, updatable = false)
    private Customers customer;
}
