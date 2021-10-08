package platform.codingnomads.co.springdata.lab_complete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.lab_complete.domain.Route;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findByOrigin_code(String code);

    List<Route> findByDestination_code(String code);

    Route findByCode(String code);
}
