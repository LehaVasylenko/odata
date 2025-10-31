package odata.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

// -----------------------------
// CUSTPARTPRICE (special price by part)
// Key: FROMDATE + PART
// -----------------------------
@Embeddable
class CustPartPriceId implements Serializable {
    @Column(name = "FROMDATE")
    private OffsetDateTime fromDate; // Date

    @Column(name = "PART")
    private Long part;

    @Override public boolean equals(Object o){
        if(this==o) return true; if(!(o instanceof CustPartPriceId)) return false;
        CustPartPriceId that=(CustPartPriceId)o; return Objects.equals(fromDate, that.fromDate) && Objects.equals(part, that.part);
    }
    @Override public int hashCode(){ return Objects.hash(fromDate, part); }
}
