package platform.codingnomads.co.ioc.lab.initial;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("platform.codingnomads.co.ioc.lab.initial")
public class CodingNomadConfiguration {

    @Bean
    public Framework framework() {
        return Framework.builder().name("Spring Boot").version("2.5").build();
    }

    @Bean
    public IDE ide() {
        return IDE.builder().name("IntelliJ IDEA").version("2021.1").build();
    }

    @Bean
    public JDK jdk() {
        return JDK.builder().name("OpenJDK").version("11").build();
    }

    @Bean
    public Keyboard keyboard() {
        return Keyboard.builder().keys("104").type("Mechanical").build();
    }

    @Bean
    public Monitor monitor() {
        return Monitor.builder().company("Asus").model("TUF Gaming").build();
    }
}