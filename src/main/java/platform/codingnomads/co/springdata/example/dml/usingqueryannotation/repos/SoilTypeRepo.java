package platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.SoilType;

@Repository
public interface SoilTypeRepo extends JpaRepository<SoilType, Long> {
}
