package platform.codingnomads.co.corespring.examples.application_context.frameworkevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FrameworkEventListenerDemo {
    public static void main(String[] args) {
        SpringApplication.run(FrameworkEventListenerDemo.class, args);
    }
}
