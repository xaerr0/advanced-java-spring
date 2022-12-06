package platform.codingnomads.co.springdata.example.ddl.onetoone.unidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sandwiches")
@NoArgsConstructor
@Getter
@Setter
public class Sandwich {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @OneToOne
    private Driver driver;
}