package platform.codingnomads.co.ioc.examples.setterinjection;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SetterInjectionDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(LaptopConfiguration.class);
        Laptop laptop = ctx.getBean(Laptop.class);
        System.out.println(laptop.printLaptopConfiguration());
    }
}
