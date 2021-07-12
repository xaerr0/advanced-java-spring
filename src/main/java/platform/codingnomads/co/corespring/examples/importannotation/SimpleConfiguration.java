package platform.codingnomads.co.corespring.examples.importannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfiguration {
    @Bean
    public SpringDeveloper springDeveloper() {
        return new SpringDeveloper();
    }
}
