package platform.codingnomads.co.corespring.examples.springbeans;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//TODO Why no work???
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBeansDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeansDemoConfig.class);
        SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
        System.out.println("Spring Developer Address: " + springDeveloper.getAddress().getStreetNumber() + " " +
                           springDeveloper.getAddress().getStreet());

        //TODO Didn't work either :(
        SpringDeveloper springDeveloper2 = ctx.getBean(SpringDeveloper.class);
        // to test
        System.out.println(springDeveloper2.getMeal().getMain() + springDeveloper2.getMeal().getSide());

    }
}