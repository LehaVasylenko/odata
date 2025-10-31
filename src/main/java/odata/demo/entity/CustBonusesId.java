package odata.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class CustBonusesId implements Serializable {
    @Column(name = "PART")
    private Long part;
    @Column(name = "LINE")
    private Long line;
}
