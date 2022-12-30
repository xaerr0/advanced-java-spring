package platform.codingnomads.co.springdata.lab.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cafes")
public class Cafe implements Serializable {

    private static final long serialVersionUID = 2768011666395634522L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    private Area area;

    @ManyToMany
    @JoinTable(name = "cafe_route", joinColumns = @JoinColumn(name = "cafe_id"),
    inverseJoinColumns = @JoinColumn(name = "route_id"))
    private List<Route> routes;


    public Cafe(String name, int rating, Area area, List<Route> routes) {
        this.name = name;
        this.rating = rating;
        this.area = area;
        this.routes = routes;

    }



    public Cafe(String name, int rating, List<Route> routes) {
        this.name = name;
        this.rating = rating;
        this.routes = routes;

    }

    @Override
    public String toString() {
        return "Cafe{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", rating=" + rating +
               '}';
    }
}