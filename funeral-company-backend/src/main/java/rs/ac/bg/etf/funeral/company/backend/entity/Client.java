package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientID;

    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    @Column(nullable = false)
    private String city;
}
