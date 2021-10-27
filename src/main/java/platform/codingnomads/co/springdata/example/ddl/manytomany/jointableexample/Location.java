package platform.codingnomads.co.springdata.example.ddl.manytomany.jointableexample;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private Long latitude;

    @Column(nullable = false)
    private Long longitude;

    //many to many annotation defers to the locations field in the Post class
    @ManyToMany
    private Set<Post> posts;
}
