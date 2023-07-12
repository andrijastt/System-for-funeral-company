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
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long modelID;

    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            nullable = false
    )
    private String description;

    @OneToMany(mappedBy = "product")
    @Transient
    @JsonIgnore
    List<Product> productList;

    public void addProduct(Product product){
        if(productList == null) productList = new ArrayList<>();
        productList.add(product);
        product.setModel(this);
    }
    public void removeProduct(Product product){
        if(productList != null){
            productList.remove(product);
            product.setModel(null);
        }
    }
}
