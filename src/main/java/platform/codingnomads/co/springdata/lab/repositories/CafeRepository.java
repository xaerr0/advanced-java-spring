package platform.codingnomads.co.springdata.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.lab.models.Cafe;

import java.util.List;


public interface CafeRepository extends JpaRepository<Cafe, Long> {

    List<Cafe> findByNameContaining(String name);


}