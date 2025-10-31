package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "CUSTFOBORDERS")
public class CustFobOrders {
    @Id
    @Column(name = "FOBORDNAME", length = 16)
    private String fobOrdName;

    @Column(name = "CURDATE")
    private OffsetDateTime curDate;
    @Column(name = "NAME", length = 37)
    private String contactName;
    @Column(name = "SUPNAME", length = 16)
    private String supName;
    @Column(name = "SUPDES", length = 48)
    private String supDes;
    @Column(name = "SUPCONTACTNAME", length = 37)
    private String supContactName;
    @Column(name = "REFERENCE", length = 16)
    private String reference;
    @Column(name = "QPRICE", precision = 16, scale = 2)
    private BigDecimal qprice;
    @Column(name = "CODE", length = 3)
    private String currency;
    @Column(name = "ORDSTATUSDES", length = 12)
    private String ordStatusDes;
    @Column(name = "FOBORD")
    private Long fobOrd; // AutoUnique

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
