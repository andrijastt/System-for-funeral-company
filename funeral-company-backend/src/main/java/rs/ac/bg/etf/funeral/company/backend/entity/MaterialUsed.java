package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUsed {

    @EmbeddedId
    private MaterialUsedPK materialUsedPK;

    @Column(nullable = false)
    @Min(value = 1)
    private int amount;
}
