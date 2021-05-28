package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.plantexample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "plants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private SoilType favoriteSoilType;

    private String sunType;

    @Column(nullable = false)
    private boolean fruitBearing;

    private Integer numDaysTillRipeFruit;

    @PrePersist
    @PreUpdate
    private void checkForIllegalState() {
        if(!fruitBearing || !Objects.isNull(numDaysTillRipeFruit)) {
            throw new IllegalStateException("If the plant is not fruit bearing you cannot set numDaysTillRipeFruit");
        }
    }
}
