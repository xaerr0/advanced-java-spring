package platform.codingnomads.co.springdata.lab_complete.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "routes")
public class Route implements Serializable {

    private static final long serialVersionUID = -3340977616965447359L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "route_generator")
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_area_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_routes_origin_area_id")
    )
    private Area origin;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_area_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_routes_destination_area_id")
    )
    private Area destination;

    @ManyToMany(mappedBy = "routes")
    private List<PointOfInterest> pointsOfInterest;

    // move @Builder so it uses the custom constructor
    @Builder
    public Route(Area origin, Area destination) {
        this.origin = origin;
        this.destination = destination;
        this.code = (origin.getCode() + "-" + destination.getCode());
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                '}';
    }
}
