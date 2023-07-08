package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplyID;

    @Column(nullable = false)
    private Date dateOrdered;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Date dateArrived;

    @OneToMany
    private List<MaterialSupply> materialSupplyList;

    public void addMaterialSupply(MaterialSupply materialSupply){
        if(materialSupplyList == null) materialSupplyList = new ArrayList<>();
        materialSupplyList.add(materialSupply);
        materialSupply.getMaterialSupplyPK().setSupplyID(this.supplyID);
    }
    public void removeMaterialSupply(MaterialSupply materialSupply){
        if(materialSupplyList != null){
            materialSupplyList.remove(materialSupply);
            materialSupply.getMaterialSupplyPK().setSupplyID(null);
        }
    }
}
