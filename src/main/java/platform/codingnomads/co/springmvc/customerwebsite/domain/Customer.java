package platform.codingnomads.co.springmvc.customerwebsite.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Builder
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String emailAddress;
    private Integer age;
    private String address;

    @OneToOne(mappedBy = "customer")
    @JoinColumn(name = "car_id")
    private RentalCar car;

}
