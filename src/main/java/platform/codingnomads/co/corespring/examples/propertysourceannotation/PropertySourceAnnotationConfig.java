package platform.codingnomads.co.corespring.examples.propertysourceannotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@ComponentScan
@PropertySource(value = "application.properties", ignoreResourceNotFound = false)
public class PropertySourceAnnotationConfig {

}