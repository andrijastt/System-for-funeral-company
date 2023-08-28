package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1)
    private Long height;

    @Column(nullable = false)
    @Min(value = 1)
    private Long width;

    @Column(nullable = false)
    @Min(value = 1)
    private Long depth;

    @Column(nullable = false)
    @Min(value = 0)
    private Long count;

    @Column(nullable = false)
    @Min(value = 1)
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

    @OneToMany
    List<Item> itemList;

    public void addItem(Item item){
        if(itemList == null) itemList = new ArrayList<>();
        itemList.add(item);
        item.getItemPK().setProductID(this.productID);
    }
    public void removeItem(Item item){
        if(itemList != null){
            itemList.remove(item);
            item.getItemPK().setProductID(null);
        }
    }
}
