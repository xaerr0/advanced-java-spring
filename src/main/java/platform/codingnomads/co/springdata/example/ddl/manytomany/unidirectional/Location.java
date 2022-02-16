package platform.codingnomads.co.springdata.example.ddl.manytomany.unidirectional;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long latitude;

    @Column(nullable = false)
    private Long longitude;
}
