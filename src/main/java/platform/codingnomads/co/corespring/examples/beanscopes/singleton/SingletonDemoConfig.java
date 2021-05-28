package platform.codingnomads.co.corespring.examples.beanscopes.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SingletonDemoConfig {
    @Bean
    @Scope(value = "singleton")
    public CodeWarrior codeWarrior() {
        return new CodeWarrior();
    }
}
