package platform.codingnomads.co.corespring.examples.primaryannotation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PrimaryAnnotationDemo {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(PrimaryAnnotationDemo.class);
        ctx.close();
    }
}
