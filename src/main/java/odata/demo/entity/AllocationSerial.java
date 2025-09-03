package odata.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="allocation_serial")
public class AllocationSerial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="allocation_fk")
    private LineAllocation allocation;

    @Column(name="serial_no", nullable=false, length=64)
    private String serialNo;

    @Column(name="condition_code", length=16) // NEW/USED/DEFECT и т.п.
    private String conditionCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LineAllocation getAllocation() {
        return allocation;
    }

    public void setAllocation(LineAllocation allocation) {
        this.allocation = allocation;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }
}

