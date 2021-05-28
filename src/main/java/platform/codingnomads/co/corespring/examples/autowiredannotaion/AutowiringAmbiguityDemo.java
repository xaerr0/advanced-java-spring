package platform.codingnomads.co.corespring.examples.autowiredannotaion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutowiringAmbiguityDemo {
    public static void main(String[] args) {
        SpringApplication.run(AutowiringAmbiguityDemo.class);
    }
}
