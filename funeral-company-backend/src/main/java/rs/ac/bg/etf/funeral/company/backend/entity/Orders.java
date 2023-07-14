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
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @Column(nullable = false)
    private String status;

    @ManyToOne(
        cascade = CascadeType.REMOVE,
        fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "contractID",
            referencedColumnName = "contractID"
    )
    private Contract contract;

    @Column(nullable = false)
    private Date dateSend;

    private Date dateArrived;

    @OneToMany
    private List<Item> itemList;

    public void addItem(Item item){
        if(itemList == null) itemList = new ArrayList<>();
        itemList.add(item);
        item.getItemPK().setOrderID(this.orderID);
    }
    public void removeItem(Item item){
        if(itemList != null){
            itemList.remove(item);
            item.getItemPK().setOrderID(null);
        }
    }
}
