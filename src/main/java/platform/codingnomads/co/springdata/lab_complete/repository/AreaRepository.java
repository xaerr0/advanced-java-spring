package platform.codingnomads.co.springdata.lab_complete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.lab_complete.domain.Area;

public interface AreaRepository extends JpaRepository<Area,Long> {

    Area findByCode(String code);
}
