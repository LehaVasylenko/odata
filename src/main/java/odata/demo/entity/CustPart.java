package odata.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTPART")
public class CustPart {
    @Id
    @Column(name = "PART")
    private Long part;

    @Column(name = "PARTNAME", length = 22)
    private String partName;
    @Column(name = "PARTDES", length = 59)
    private String partDes;
    @Column(name = "CUSTPARTNAME", length = 22)
    private String custPartName;
    @Column(name = "CUSTPARTDES", length = 48)
    private String custPartDes;
    @Column(name = "CUSTPARTBARCODE", length = 22)
    private String custPartBarcode;
    @Column(name = "NOTVALID", length = 1)
    private String notValid;
    @Column(name = "PACKCODE", length = 8)
    private String packCode;
    @Column(name = "PACKNAME", length = 48)
    private String packName;
    @Column(name = "QRANKCODE", length = 4)
    private String qrankCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST", referencedColumnName = "CUST")
    private Customers customer;
}
