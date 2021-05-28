package platform.codingnomads.co.corespring.examples.beanannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAnnotationConfig {
    @Bean(initMethod = "init", destroyMethod = "cleanup", name = "our_friendly_code_warrior")
    public CodeWarrior codeWarrior() {
        return new CodeWarrior();
    }
}
