package platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "plants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraph(name = "Plant.noSoilType")
@ToString
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false, fetch = FetchType.EAGER)
    private SoilType favoriteSoilType;

    private String sunType;

    @Column(nullable = false)
    private boolean fruitBearing;

    private Integer numDaysTillRipeFruit;

    @PrePersist
    @PreUpdate
    private void checkForIllegalState() {
        if(!fruitBearing && !Objects.isNull(numDaysTillRipeFruit)) {
            throw new IllegalStateException("If the plant is not fruit bearing you cannot set numDaysTillRipeFruit");
        }
    }
}
