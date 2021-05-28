package platform.codingnomads.co.corespring.examples.springbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CodeWarrior.class)
public class SpringBeansDemoConfig {
    @Bean
    public Address address() {
        return new Address("Codingnomads Street", 1500);
    }
}
