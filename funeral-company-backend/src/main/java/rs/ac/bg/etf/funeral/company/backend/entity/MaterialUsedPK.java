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
public class MaterialUsedPK implements Serializable {

    @Column(name = "materialID", nullable = false)
    private Long materialID;

    @Column(name = "productID", nullable = false)
    private Long productID;
}
