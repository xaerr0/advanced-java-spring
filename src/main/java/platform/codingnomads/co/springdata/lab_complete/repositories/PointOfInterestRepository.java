package platform.codingnomads.co.springdata.lab_complete.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.lab_complete.models.PointOfInterest;

import java.util.List;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

    List<PointOfInterest> findAllByArea_code(String code);

    List<PointOfInterest> findAllDistinctByRoutes_codeContaining(String code);
}
