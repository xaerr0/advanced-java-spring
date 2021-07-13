package platform.codingnomads.co.corespring.lab.complete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:xml-config/records.xml"})
public class CoreLabConfig {

    @Bean
    public Turntable turntable() {
        return new Turntable("Technics", "SL-1210M5G");
    }
}
