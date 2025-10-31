package odata.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTWEEKDAY")
public class CustWeekday {
    @Id
    @Column(name = "WEEKDAY", length = 3)
    private String weekday;

    @Column(name = "FROMTIME", length = 5)
    private String fromTime;
    @Column(name = "TOTIME", length = 5)
    private String toTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}