package platform.codingnomads.co.corespring.examples.ImportAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfiguration {

    @Bean
    public CodeWarrior codeWarrior() {
        return new CodeWarrior();
    }
}
