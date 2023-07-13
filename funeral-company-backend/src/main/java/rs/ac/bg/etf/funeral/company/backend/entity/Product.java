package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column(nullable = false)
    private Long height;

    @Column(nullable = false)
    private Long width;

    @Column(nullable = false)
    private Long depth;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn(
            name = "modelID",
            referencedColumnName = "modelID"
    )
    private Model model;

    @OneToMany
    List<MaterialUsed> materialUsedList;

    public void addMaterialUsed(MaterialUsed materialUsed){
        if(materialUsedList == null) materialUsedList = new ArrayList<>();
        materialUsedList.add(materialUsed);
        materialUsed.getMaterialUsedPK().setProductID(this.productID);
    }
    public void removeMaterialSupply(MaterialUsed materialUsed){
        if(materialUsedList != null){
            materialUsedList.remove(materialUsed);
            materialUsed.getMaterialUsedPK().setProductID(null);
        }
    }
}
