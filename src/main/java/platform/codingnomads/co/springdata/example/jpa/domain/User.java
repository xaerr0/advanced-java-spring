package platform.codingnomads.co.springdata.example.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetail userDetail;

    @Column(name = "name")
    private String name;
}
