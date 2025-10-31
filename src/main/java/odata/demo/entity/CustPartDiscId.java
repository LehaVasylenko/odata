package odata.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// -----------------------------
// CUSTPARTDISC (discounts by part)
// Key: DISCOUNT + PART
// -----------------------------
@Embeddable
class CustPartDiscId implements Serializable {
    @Column(name = "DISCOUNT")
    private Long discount;

    @Column(name = "PART")
    private Long part;

    // equals & hashCode
    @Override public boolean equals(Object o){
        if(this==o) return true; if(!(o instanceof CustPartDiscId)) return false;
        CustPartDiscId that=(CustPartDiscId)o; return Objects.equals(discount, that.discount) && Objects.equals(part, that.part);
    }
    @Override public int hashCode(){ return Objects.hash(discount, part); }
}
