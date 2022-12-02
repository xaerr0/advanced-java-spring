package platform.codingnomads.co.corespring.lab;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CoreLabApplication2 {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(CoreLabConfig.class);
        Restaurant restaurant = ctx.getBean(Restaurant.class);

        System.out.println("The best place to get your BBQ Shoes at is " + restaurant.getName() +
                           " and you can place your online orders at " + restaurant.getWebsite());

        String[] meats = ctx.getBeanNamesForType(Menu.class);
        String[] customers = ctx.getBeanNamesForType(Customer.class);

        for (String meat : meats) {
            System.out.println("Meat item: " + ctx.getBean(meat, Menu.class).getMeat() + " is $" +
                               ctx.getBean(meat, Menu.class).getPriceLb() + "lb");
        }
        System.out.println("Our only customers are ");
        for (String customer : customers) {
            System.out.println(ctx.getBean(customer, Customer.class).getFirstName() + " " +
                               ctx.getBean(customer, Customer.class).getLastName());
        }
    }
}