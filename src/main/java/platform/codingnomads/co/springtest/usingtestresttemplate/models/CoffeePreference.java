package platform.codingnomads.co.springtest.usingtestresttemplate.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeePreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private boolean sugar;

    @Column(nullable = false)
    private boolean iced;

    @Column(nullable = false)
    private Size size;

    @Column(nullable = false)
    private int intensity;

    public void setIntensity(int intensity) {

        if(intensity < 0 || intensity > 10) {
            throw new IllegalStateException("Intensity must be between 0 and 10");
        }

        this.intensity = intensity;
    }

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }
}
