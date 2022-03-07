package platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.Plant;

import java.util.ArrayList;

@Repository
public interface PlantRepo extends JpaRepository<Plant, Long> {

    //////////////// NO VARIABLE JPQL AND SQL ////////////////

    //use JPQL to query the database for a plant and its soil_type with JOIN
    @Query(value = "SELECT p, st FROM Plant p JOIN SoilType st ON p.favoriteSoilType = st.id")
    ArrayList<Plant> findPlantEagerLoadSoilType();

    //use JPQL to query only the plant not the soil type
    @Query("SELECT p FROM Plant p")
    ArrayList<Plant> getPlantsNoSoilType();

    //use JPQL to query only fruit bearing plants without their soil types
    @Query("SELECT p FROM Plant p WHERE fruitBearing = true")
    ArrayList<Plant> findFruitBearingPlants();

    //use native SQL to query the plant with id 1 without its soil type
    @Query(value = "SELECT * FROM plants WHERE id = 1", nativeQuery = true)
    Plant findPlantWithIdOne();

    @Query(value = "SELECT count(id) FROM plants", nativeQuery = true)
    long findNumberOfPlants();

    //////////////// VARIABLE SQL AND JPQL ////////////////

    @Query("SELECT p FROM Plant p WHERE id = ?1")
    Plant getPlantById(Long id);

    @Query("SELECT p FROM Plant p WHERE name = ?1")
    Plant getPlantByName(String plantName);

    @Query("SELECT p FROM Plant p WHERE numDaysTillRipeFruit > ?1 AND numDaysTillRipeFruit < ?2")
    ArrayList<Plant> getPlantsBasedOnNumDaysTillRipeFruitRange(int minDays, int maxDays);

    @Query("SELECT p FROM Plant p WHERE fruitBearing = :fruitBearing")
    ArrayList<Plant> getPlantBasedOnFruitBearing(@Param("fruitBearing") boolean fruitBearing);

    @Query("SELECT p, st FROM Plant p JOIN SoilType st ON p.favoriteSoilType = st.id WHERE st.name = :soilTypeName AND p.fruitBearing = :fruitBearing")
    ArrayList<Plant> getPlantsBasedOnSoilTypeAndFruitBearingness(@Param("fruitBearing") boolean fruitBearing, @Param("soilTypeName") String soilTypeName);

    //////////////// SORTING USING @QUERY ////////////////

    //get all fruit bearing plants with the option to sort them
    @Query("SELECT p FROM Plant p WHERE fruitBearing = true")
    ArrayList<Plant> getFruitBearingPlants(Sort sort);

    //get all plants with a particular sun type with the option to sort
    @Query("SELECT p FROM Plant p WHERE sunType = ?1")
    ArrayList<Plant> getPlantsBasedOnSunType(String sunType, Sort sort);

    //get plants based on sun type, fruit bearingness and a ph greater than a lower bound with the option to sort
    @Query("SELECT p, st FROM Plant p JOIN SoilType st ON p.favoriteSoilType = st.id WHERE p.sunType = ?1 AND p.fruitBearing = ?2 AND st.ph > ?3")
    ArrayList<Plant> getPlantsBasedOnSunTypeAndFruitBearingnessAndPhGreaterThan(String sunType, boolean fruitBearing, double lowerPhBound, Sort sort);

    //////////////// PAGINATION USING @QUERY ////////////////

    //page through all plants. this particular query is already present in the
    // PagingAndSortingRepository, but it is a good beginner example.
    @Query("SELECT p FROM Plant p")
    Page<Plant> getPagedPlants(Pageable pageable);

    //page through all plants with a certain soil type name
    @Query(
            //actual query used to return data
            value = "SELECT p FROM Plant p JOIN SoilType st ON p.favoriteSoilType = st.id WHERE st.name = ?1"
            //query used to count the number of results for pagination purposes
            //countQuery =
            // "SELECT count(p.id) FROM Plant p JOIN SoilType st ON p.favoriteSoilType = st.id WHERE st.name = ?1"
    )
    Page<Plant> getAllPlantsWithSoilTypeNameIs(String soilTypeName, Pageable pageable);

    //page through all fruit bearing or non-fruit bearing plants
    @Query(value = "SELECT p FROM Plant p WHERE fruitBearing = ?1")
    Page<Plant> getPagedPlantsToggleFruitBearing(boolean fruitBearing, Pageable pageable);

    @Query(
            //native sql select statement
            value = "SELECT p FROM Plant p JOIN SoilType st ON p.favoriteSoilType = st.id WHERE ph < ?1",
            //native sql count query that returns the number of plants with soil type with less than phUpperBound
            countQuery = "SELECT count(p.id) FROM Plant p JOIN SoilType st ON p.favoriteSoilType = st.id WHERE ph < ?1"
    )
    Page<Plant> getPlantsWithPhLessThan(double phUpperBound, Pageable pageable);

}
