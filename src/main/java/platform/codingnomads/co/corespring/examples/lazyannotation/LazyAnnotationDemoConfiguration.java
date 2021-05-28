package platform.codingnomads.co.corespring.examples.lazyannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@ComponentScan(basePackages = "platform.codingnomads.co.corespring.examples.lazyannotation")
public class LazyAnnotationDemoConfiguration {
    @Bean
    public CodeWarrior codeWarrior() {
        return new CodeWarrior();
    }

}
