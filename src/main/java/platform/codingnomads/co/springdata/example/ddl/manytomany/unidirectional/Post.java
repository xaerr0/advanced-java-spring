package platform.codingnomads.co.springdata.example.ddl.manytomany.unidirectional;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    //relate Comment and Post using @OneToMany
    @OneToMany(cascade = CascadeType.ALL)
    //force use of join column
    @JoinColumn(
            //use name post_id instead of comments_id
            name = "post_id",
            //force the use of the join column aka no comments without a related post
            nullable = false
    )
    private Set<Comment> comments;

    //set up many-to-many relationship with the tag class
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Location> location;
}