package rs.ac.bg.etf.funeral.company.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "name_constraint",
                columnNames = "name"
        )
)
public class Category {

    @Id
    @SequenceGenerator(
            sequenceName = "category_sequence",
            name = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "category_sequence"
    )
    private Long categoryID;
    @Column(
            nullable = false
    )
    private String name;

    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Transient
    @JsonIgnore
    List<Material> materials;

    public void addMaterials(Material material){
        if(materials == null) materials = new ArrayList<>();
        materials.add(material);
        material.setCategory(this);
    }
    public void removeMaterial(Material material){
        if(materials != null){
            materials.remove(material);
            material.setCategory(null);
        }
    }
}
