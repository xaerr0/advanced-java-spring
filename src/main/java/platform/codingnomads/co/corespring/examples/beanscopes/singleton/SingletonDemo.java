package platform.codingnomads.co.corespring.examples.beanscopes.singleton;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SingletonDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SingletonDemoConfig.class);
        ctx.refresh();

        CodeWarrior codeWarrior1 = ctx.getBean(CodeWarrior.class);
        System.out.println(codeWarrior1.hashCode());

        CodeWarrior codeWarrior2 = ctx.getBean(CodeWarrior.class);
        System.out.println(codeWarrior2.hashCode());

        ctx.close();
    }
}
