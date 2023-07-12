package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long length;

    @Column(nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn(
            name = "materialID",
            referencedColumnName = "materialID"
    )
    private Material material;

    @ManyToOne
    @JoinColumn(
            name = "modelID",
            referencedColumnName = "modelID"
    )
    private Model model;
}
