package platform.codingnomads.co.springdata.lab_complete.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "areas")
@Builder
public class Area implements Serializable {

    // this field (and implementing Serializable) is not required here,
    // but added for an early introduction to the concept
    private static final long serialVersionUID = 5217291127304415108L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "area_generator")
    private Long id;

    // make this column unique, so no two areas share a code
    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "area")
    private List<PointOfInterest> pointsOfInterest;

    public void addPointOfInterest(PointOfInterest pointOfInterest) {
        if (this.pointsOfInterest == null) {
            this.pointsOfInterest = new ArrayList<>(Collections.singletonList(pointOfInterest));
        } else {
            this.pointsOfInterest.add(pointOfInterest);
        }
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", pointsOfInterest=" + pointsOfInterest +
                '}';
    }

    public String toStringWithoutPoi() {
        return "Area{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
