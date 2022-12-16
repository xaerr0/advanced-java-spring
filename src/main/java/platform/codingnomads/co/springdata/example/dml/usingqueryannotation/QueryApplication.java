package platform.codingnomads.co.springdata.example.dml.usingqueryannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repositories.SoilTypeRepo;

@SpringBootApplication
public class QueryApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class);
    }

    @Autowired
    PlantService plantService;

    @Autowired
    SoilTypeService soilTypeService;

    @Override
    public void run(String... args) throws Exception {

        soilTypeService.getStuff();

//        plantService.saveStuff();
//
//        plantService.getStuff();


    }
}