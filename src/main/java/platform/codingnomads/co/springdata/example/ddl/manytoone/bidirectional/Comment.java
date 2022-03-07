package platform.codingnomads.co.springdata.example.ddl.manytoone.bidirectional;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    private Post post;

}
