package platform.codingnomads.co.springdata.example.dml.commonproblems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.example.dml.commonproblems.services.UserService;

@SpringBootApplication
public class CommonProblemsApplication implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(CommonProblemsApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.idError();
//        userService.persistAFewUsers();
//        userService.querySomeData();
    }
}
