package platform.codingnomads.co.springdata.example.ddl.manytoone.birderictional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    //this annotation references the configuration on the post field in the Comment class
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

}
