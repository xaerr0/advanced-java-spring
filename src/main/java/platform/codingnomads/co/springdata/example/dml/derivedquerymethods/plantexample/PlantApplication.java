package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.plantexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PlantApplication implements CommandLineRunner {

    @Autowired
    PlantRepo plantRepo;

    public static void main(String[] args) {
        SpringApplication.run(PlantApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        SoilType compost = SoilType.builder().name("compost").dry(false).ph(10).build();
        SoilType swamp = SoilType.builder().name("swamp").dry(false).ph(20).build();
        SoilType arid = SoilType.builder().name("arid").dry(true).ph(1).build();

        Plant tomato = Plant.builder()
                .name("tomato")
                .fruitBearing(true)
                .favoriteSoilType(compost)
                .sunType("full sun")
                .numDaysTillRipeFruit(60)
                .build();

        Plant cranberry = Plant.builder()
                .name("cranberry")
                .fruitBearing(true)
                .favoriteSoilType(swamp)
                .sunType("partial sun")
                .numDaysTillRipeFruit(100)
                .build();

        Plant cactus = Plant.builder()
                .name("cactus")
                .fruitBearing(true)
                .favoriteSoilType(arid)
                .sunType("full sun")
                .numDaysTillRipeFruit(180)
                .build();

        plantRepo.save(tomato);
        plantRepo.save(cranberry);
        plantRepo.save(cactus);

        // DEMONSTRATE USE OF DERIVED QUERY METHODS

        System.out.println("\n********* findByName() *********");
        List<Plant> plants1 = plantRepo.findByName("cactus");
        plants1.forEach(System.out::println);

        System.out.println("\n********* findByFruitBearingAndFavoriteSoilType_dry *********");
        List<Plant> plants2 = plantRepo.findByFruitBearingAndFavoriteSoilType_dry(true, true);
        plants2.forEach(System.out::println);

        System.out.println("\n********* findByNameEndingWith() *********");
        List<Plant> plants3 = plantRepo.findByNameEndingWith("berry");
        plants3.forEach(System.out::println);

        System.out.println("\n********* findFirstByFavoriteSoilType_dryIsTrue() *********");
        Plant plant4 = plantRepo.findFirstByFavoriteSoilType_dryIsTrue();
        System.out.println(plant4.toString());

        plantRepo.deleteAll();
    }
}
