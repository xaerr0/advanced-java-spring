package platform.codingnomads.co.ioc.lab.completed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("platform.codingnomads.co.ioc.lab.completed")
public class CodeWarriorConfiguration {

    @Bean
    public Framework framework() {
        return Framework.builder().name("Spring Boot").version("2.2").build();
    }

    @Bean
    public IDE ide() {
        return IDE.builder().name("IntelliJ IDEA ").version("2019.3").build();
    }

    @Bean
    public JDK jdk() {
        return JDK.builder().name("Open JDK").version("11").build();
    }
}
