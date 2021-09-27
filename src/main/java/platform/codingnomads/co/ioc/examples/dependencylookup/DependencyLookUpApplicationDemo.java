package platform.codingnomads.co.ioc.examples.dependencylookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DependencyLookUpApplicationDemo {
    public static void main(String[] args) {
        SpringApplication.run(DependencyLookUpApplicationDemo.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(IOCDemoConfiguration.class);
        GreetingRenderer greetingRenderer = ctx.getBean("renderer", GreetingRenderer.class);
        greetingRenderer.render();
    }
}
