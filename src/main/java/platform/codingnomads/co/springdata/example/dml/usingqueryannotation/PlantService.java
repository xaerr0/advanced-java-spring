package platform.codingnomads.co.springdata.example.dml.usingqueryannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.Plant;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.SoilType;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repositories.PlantRepo;

import java.util.ArrayList;

@Service
public class PlantService {

    @Autowired
    PlantRepo plantRepo;

    @Transactional
    public void saveStuff() {

        if (plantRepo.findAll().isEmpty()) {

            SoilType soilType = SoilType.builder().dry(true).ph(7.6).name("tester").build();
            SoilType soilType2 = SoilType.builder().dry(false).ph(7.2).name("tester2").build();

            Plant plant = Plant.builder().name("test plant").sunType("full")
                    .fruitBearing(true).favoriteSoilType(soilType).build();
            plantRepo.save(plant);

            Plant plant1 = Plant.builder().name("test 1 plant").sunType("full").numDaysTillRipeFruit(5)
                    .fruitBearing(true).favoriteSoilType(soilType2).build();
            plantRepo.save(plant1);

            Plant plant2 = Plant.builder().name("thingy").sunType("shade")
                    .fruitBearing(false).favoriteSoilType(soilType).build();
            plantRepo.save(plant2);

            Plant plant3 = Plant.builder().name("test 3 plant").sunType("full").numDaysTillRipeFruit(10)
                    .fruitBearing(true).favoriteSoilType(soilType2).build();
            plantRepo.save(plant3);

            Plant plant4 = Plant.builder().name("test 4 plant").sunType("full").numDaysTillRipeFruit(2)
                    .fruitBearing(true).favoriteSoilType(soilType).build();
            plantRepo.save(plant4);
        }
    }

    @Transactional
    public void getStuff() {

        System.out.println("SORTED FRUIT BEARING PLANTS");
        ArrayList<Plant> plants = plantRepo.getFruitBearingPlants(Sort.by(Sort.Order.desc("id")));

        for (Plant p : plants) {
            System.out.println(p.toString());
        }

        //set up a page request
        Pageable pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("numDaysTillRipeFruit")));
        Page<Plant> page;

        //use the page variable to cycle through the pages
        do {
            //get the page from the database
            page = plantRepo.getPlantsWithPhLessThan(8, pageRequest);

            //print the page number + 1 to offset the start being 0
            System.out.println("PAGE " + (page.getNumber() + 1));

            //print the contents of the current page
            page.getContent().forEach(System.out::println);

            //get the next page request
            pageRequest = pageRequest.next();

        } while (page.hasNext());
    }
}
