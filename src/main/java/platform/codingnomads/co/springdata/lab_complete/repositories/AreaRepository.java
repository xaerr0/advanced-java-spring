package platform.codingnomads.co.springdata.lab_complete.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.lab_complete.models.Area;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Area findByCode(String code);

    List<Area> findAllByPointsOfInterest_typeIgnoreCase(String name);
}
