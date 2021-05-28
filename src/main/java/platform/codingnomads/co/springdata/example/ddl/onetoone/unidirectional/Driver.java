package platform.codingnomads.co.springdata.example.ddl.onetoone.unidirectional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "drivers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @OneToOne
    private Car car;
}