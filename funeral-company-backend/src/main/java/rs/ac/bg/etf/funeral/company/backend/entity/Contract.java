package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractID;

    @Column(nullable = false)
    private Date dateSigned;

    @Column(nullable = false)
    private Date paymentDate;

    @Column(nullable = false)
    private Date validUntil;

    @Column(nullable = false)
    private Float money;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private Boolean valid;

    @ManyToOne(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "clientID",
            referencedColumnName = "clientID"
    )
    @ToString.Exclude
    private Client client;
}
