package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.plantexample;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "soil_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SoilType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private long ph;

    @Column(nullable = false)
    private boolean dry;
}
