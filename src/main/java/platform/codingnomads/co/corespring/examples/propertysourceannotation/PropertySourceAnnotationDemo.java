package platform.codingnomads.co.corespring.examples.propertysourceannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class PropertySourceAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(PropertySourceAnnotationConfig.class);
        ctx.refresh();
        final App app = ctx.getBean(App.class);
        final Computer computer = ctx.getBean(Computer.class);
        System.out.println("Values from myapp.properties: " +
                "App Name: " + app.getAppName() + ", App Version: " + app.getAppVersion());

        System.out.println("Values from computer.properties: " + "Monitor: " + computer.getMonitor() + " and " +
                           "Keyboard: " + computer.getKeyboard());
        ctx.close();
    }
}