package platform.codingnomads.co.corespring.examples.ImportAnnotation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SimpleConfiguration.class)
public class ImportAnnotationConfig {
    @Bean
    public Framework framework() {
        return new Framework();
    }
}
