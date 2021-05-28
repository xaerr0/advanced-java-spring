package platform.codingnomads.co.corespring.examples.beanannotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BeanAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanAnnotationConfig.class);
        ctx.refresh();
        CodeWarrior codeWarrior1 = ctx.getBean("our_friendly_code_warrior", CodeWarrior.class);
        ctx.close();
    }
}
