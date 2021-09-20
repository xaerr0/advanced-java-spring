package platform.codingnomads.co.springmvc.customerwebsite.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
@Builder
@Getter
@Setter
public class RentalCar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String color;
    private String make;
    private String model;
    private String license;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return (color + " " + make + " " + model + " - " + license);
    }
}
