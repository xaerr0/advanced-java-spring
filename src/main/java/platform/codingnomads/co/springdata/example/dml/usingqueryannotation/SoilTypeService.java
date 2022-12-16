package platform.codingnomads.co.springdata.example.dml.usingqueryannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.SoilType;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repositories.SoilTypeRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SoilTypeService {

    @Autowired
    SoilTypeRepo soilTypeRepo;

    @Transactional
    public void getStuff() {


        System.out.println("------Get SoilType by Id------");
        SoilType soilType = soilTypeRepo.getSoilTypeById(2L);
        System.out.println(soilType);

        System.out.println("------Get SoilType Where Dry Is True------");
        List<SoilType> getSoilTypeIfDryIsTrue = soilTypeRepo.getSoilTypeIfDryIsTrue();
        getSoilTypeIfDryIsTrue.forEach(System.out::println);

        System.out.println("------Get SoilType Where Ph is less than 7.6------");
        List<SoilType> getSoilTypeWherePhLessThan = soilTypeRepo.getSoilTypeWherePhLessThan(7.6);
        getSoilTypeWherePhLessThan.forEach(System.out::println);

        System.out.println("------Get SoilType By Id Using Colon------");
        SoilType soilType1 = soilTypeRepo.getSoilTypeByIdWithColon(1L);
        System.out.println(soilType1);
    }

    }