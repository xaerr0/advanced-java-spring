package platform.codingnomads.co.corespring.lab;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration

@ImportResource({"classpath*:xml-config/menu.xml", "classpath*:xml-config/customer.xml"})
public class CoreLabConfig {

    @Bean
    public Restaurant restaurant() {
        return new Restaurant("Hogs 'n Clogs", "www.BBQShoes.gov");
    }
}