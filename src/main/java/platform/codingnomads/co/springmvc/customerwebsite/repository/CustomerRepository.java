package platform.codingnomads.co.springmvc.customerwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;

    public interface CustomerRepository extends JpaRepository<Customer, Long> { }
