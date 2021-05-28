package platform.codingnomads.co.corespring.examples.configurationannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ConfigurationDemoConfig {
    @Bean
    public CodeWarrior codeWarrior() {
        return new CodeWarrior();
    }
}
