package platform.codingnomads.co.corespring.examples.autowiredannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AutowiringAmbiguityDemo {
    public static void main(String[] args) {
        SpringApplication.run(AutowiringAmbiguityDemo.class);
    }
}
