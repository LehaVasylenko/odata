package odata.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="line_allocation")
public class LineAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="line_fk")
    private SalesOrderLine line;

    @Column(name="allocated_qty", nullable=false, precision=18, scale=3)
    private BigDecimal allocatedQty;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="batch_fk")
    private Batch batch;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="location_fk")
    private Location location;

    @OneToMany(mappedBy="allocation", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<AllocationSerial> serials = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesOrderLine getLine() {
        return line;
    }

    public void setLine(SalesOrderLine line) {
        this.line = line;
    }

    public BigDecimal getAllocatedQty() {
        return allocatedQty;
    }

    public void setAllocatedQty(BigDecimal allocatedQty) {
        this.allocatedQty = allocatedQty;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<AllocationSerial> getSerials() {
        return serials;
    }

    public void setSerials(List<AllocationSerial> serials) {
        this.serials = serials;
    }
}
