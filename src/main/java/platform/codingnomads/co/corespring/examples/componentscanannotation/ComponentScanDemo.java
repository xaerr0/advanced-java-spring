package platform.codingnomads.co.corespring.examples.componentscanannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanDemo {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfiguration.class);
        isBeanPresent("framework", "JDK", "motherboard", "OS", "sampleBean","componentScanConfiguration");
    }

    private static void isBeanPresent(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName + " in ApplicationContext: " +
                    applicationContext.containsBean(beanName));
        }
    }
}
