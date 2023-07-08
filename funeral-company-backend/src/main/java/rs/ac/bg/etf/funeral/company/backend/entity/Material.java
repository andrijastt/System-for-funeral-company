package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialID;

    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            nullable = false
    )
    private String unit;

    @Column(
            nullable = false
    )
    private int count;

    @Column(
            nullable = false
    )
    @Min(0)
    private Float price;

    @ManyToOne(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "categoryID",
            referencedColumnName = "categoryID"
    )
    @ToString.Exclude
    private Category category;

    @OneToMany
    private List<MaterialSupply> materialSupplyList;

    public void addMaterialSupply(MaterialSupply materialSupply){
        if(materialSupplyList == null) materialSupplyList = new ArrayList<>();
        materialSupplyList.add(materialSupply);
        materialSupply.getMaterialSupplyPK().setMaterialID(this.materialID);
    }
    public void removeMaterialSupply(MaterialSupply materialSupply){
        if(materialSupplyList != null){
            materialSupplyList.remove(materialSupply);
            materialSupply.getMaterialSupplyPK().setMaterialID(null);
        }
    }
}
