package platform.codingnomads.co.springdata.example.ddl.manytoone.bidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,updatable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)

    private Post post;
}