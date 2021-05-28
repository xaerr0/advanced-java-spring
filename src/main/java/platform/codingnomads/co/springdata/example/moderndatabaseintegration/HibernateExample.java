package platform.codingnomads.co.springdata.example.moderndatabaseintegration;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@SpringBootApplication
public class HibernateExample implements CommandLineRunner {

    @Autowired
    ManagerRepository managerRepository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateExample.class);
    }

    @Override
    public void run(String... args) {
        managerRepository.findAll()
                .forEach(manager -> System.out.println(manager.toString()));
    }
}

@Entity
@Data
@ToString
class Manager {
    private int id;
    private String firstName;
    private String lastName;
}

@Repository
interface ManagerRepository extends JpaRepository<Manager, Long> {
}

