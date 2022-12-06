package platform.codingnomads.co.springdata.example.ddl.onetoone.bidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "helmets")
@NoArgsConstructor
@Getter
@Setter
public class Helmet {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String name;
    @Column(name = "type")
    private String type;

    @OneToOne
    private Driver driver;
}