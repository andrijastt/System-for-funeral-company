package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MaterialSupplyPK implements Serializable {

    @Column(name = "materialID", nullable = false)
    private Long materialID;
    @Column(name = "supplyID", nullable = false)
    private Long supplyID;
}
