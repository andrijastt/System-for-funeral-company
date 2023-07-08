package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialSupply {

    @EmbeddedId
    private MaterialSupplyPK materialSupplyPK;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Float price;
}
