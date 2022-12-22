package platform.codingnomads.co.springdata.example.dml.lifecyclecallback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class LifecycleCallbackDemo {


    public static void main(String[] args) {
        SpringApplication.run(LifecycleCallbackDemo.class);
    }

    @Bean
    public CommandLineRunner runStuff(PrintEntityRepository printEntityRepository) {
        return (args) -> {
            // put your logic here
            PrintEntity printEntity = new PrintEntity();
            PrintEntity printEntity1;
            PrintEntity printEntity2 = new PrintEntity("Hello");

            printEntityRepository.save(printEntity);
//            printEntityRepository.save(printEntity1);
            printEntityRepository.save(printEntity2);
            System.out.println("test");

            Optional<PrintEntity> optional = printEntityRepository.findById(14L);
            if (optional.isPresent()) {
                PrintEntity printEntity3 = optional.get();
            }



        };
    }
}