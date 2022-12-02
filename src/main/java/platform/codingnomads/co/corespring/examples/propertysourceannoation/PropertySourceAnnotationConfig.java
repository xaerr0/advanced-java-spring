package platform.codingnomads.co.corespring.examples.propertysourceannoation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//TODO Help get this correct
@Configuration
@ComponentScan
@PropertySource(value = "application.properties", ignoreResourceNotFound = true)
public class PropertySourceAnnotationConfig { }