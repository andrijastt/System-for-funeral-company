package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(nullable = false)
    private Date dateArrived;
}
