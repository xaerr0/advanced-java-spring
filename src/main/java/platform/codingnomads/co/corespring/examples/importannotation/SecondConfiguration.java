package platform.codingnomads.co.corespring.examples.importannotation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondConfiguration {
    @Bean
    public SpringDeveloper springDeveloper2() {
        return new SpringDeveloper();
    }


}