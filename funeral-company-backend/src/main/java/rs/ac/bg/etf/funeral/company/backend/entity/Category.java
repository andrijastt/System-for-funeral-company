package rs.ac.bg.etf.funeral.company.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "name_constraint",
                columnNames = "name"
        )
)
public class Category {

    @Id
    @SequenceGenerator(
            sequenceName = "category_sequence",
            name = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "category_sequence"
    )
    private Long categoryID;
    @Column(
            nullable = false
    )
    private String name;

}
