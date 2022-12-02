package platform.codingnomads.co.corespring.examples.importannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import(SimpleConfiguration.class)
@Import(SecondConfiguration.class)
public class ImportAnnotationConfig {
    @Bean
    public Framework framework() {
        return new Framework();
    }

    @Bean
    public SecondConfiguration secondConfiguration() {
        return new SecondConfiguration();
    }
}