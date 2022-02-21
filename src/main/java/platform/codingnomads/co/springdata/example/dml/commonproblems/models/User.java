package platform.codingnomads.co.springdata.example.dml.commonproblems.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private ContactCard contactCard;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            optional = false, fetch = FetchType.LAZY)
    private Address address;
}
