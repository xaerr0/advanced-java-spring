package platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.SoilType;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SoilTypeRepo extends JpaRepository<SoilType, Long> {

    @Query("SELECT p FROM SoilType p WHERE id = ?1")
    SoilType getSoilTypeById(Long id);

    @Query("SELECT p FROM SoilType p WHERE dry = true")
    List<SoilType> getSoilTypeIfDryIsTrue();

    @Query(value = "SELECT * FROM Soil_types WHERE ph < 7.6", nativeQuery = true)
    List<SoilType> getSoilTypeWherePhLessThan(double ph);

    @Query(value = "SELECT p FROM SoilType p WHERE id = :id")
    SoilType getSoilTypeByIdWithColon(@Param("id")Long id);


}