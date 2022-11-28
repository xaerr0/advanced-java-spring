package platform.codingnomads.co.corespring.examples.beanscopes.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
// TODO Not sure if I did this one right....
// TODO https://platform.codingnomads.co/learn/mod/page/view.php?id=7075&forceview=1
@Configuration
public class SingletonDemoConfig {
    @Bean
    @Scope(value = "singleton")
    public SpringBean springBean() {
        return new SpringBean();
    }

    @Bean
    public SpringBean greenBean() {
        return new SpringBean();
    }
}