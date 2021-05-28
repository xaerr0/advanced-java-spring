package platform.codingnomads.co.springdata.example.dml.introducingrepositories.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftDrinkRepo extends JpaRepository<SoftDrink, Long> {

}
