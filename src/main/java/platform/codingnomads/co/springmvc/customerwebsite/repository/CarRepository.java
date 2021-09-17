package platform.codingnomads.co.springmvc.customerwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.domain.RentalCar;

public interface CarRepository extends JpaRepository<RentalCar, Long> {

    RentalCar findByCustomerId(Long id);
}
