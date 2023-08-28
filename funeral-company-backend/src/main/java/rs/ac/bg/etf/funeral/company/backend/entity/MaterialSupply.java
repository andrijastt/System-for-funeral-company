package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1)
    private int amount;

    @Column(nullable = false)
    @Min(value = 1)
    private Float price;
}
