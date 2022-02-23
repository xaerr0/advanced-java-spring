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
@Table(name = "areas")
@Builder
@ToString
public class Area implements Serializable {

    private static final long serialVersionUID = 5217291127304415108L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "area_generator")
    private Long id;

    // make this column unique, so no two areas share a code
    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "area")
    private List<PointOfInterest> pointsOfInterest;
}
