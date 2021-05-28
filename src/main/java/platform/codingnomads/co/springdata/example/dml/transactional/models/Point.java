package platform.codingnomads.co.springdata.example.dml.transactional.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "points")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int x;

    @Column(nullable = false)
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
