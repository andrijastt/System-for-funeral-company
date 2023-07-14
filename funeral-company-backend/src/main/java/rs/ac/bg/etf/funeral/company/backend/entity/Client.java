package rs.ac.bg.etf.funeral.company.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "client",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Transient
    @JsonIgnore
    List<Contract> contracts;

    public void addContract(Contract contract){
        if(contracts == null) contracts = new ArrayList<>();
        contracts.add(contract);
        contract.setClient(this);
    }
    public void removeContract(Contract contract){
        if(contracts != null){
            contracts.remove(contract);
            contract.setClient(null);
        }
    }
}
