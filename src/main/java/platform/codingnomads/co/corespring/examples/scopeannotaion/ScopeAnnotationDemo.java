package platform.codingnomads.co.corespring.examples.scopeannotaion;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ScopeAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ScopeAnnotationDemoConfig.class);
        ctx.refresh();
        CodeWarrior codeWarrior1 = ctx.getBean(CodeWarrior.class);
        CodeWarrior codeWarrior2 = ctx.getBean(CodeWarrior.class);

        System.out.println("--------Hashcode of Singleton Bean-------");
        System.out.println(codeWarrior1.hashCode());
        System.out.println(codeWarrior2.hashCode());

        final JDK jdk1 = ctx.getBean(JDK.class);
        final JDK jdk2 = ctx.getBean(JDK.class);

        System.out.println("--------Hashcode of Prototype Bean-------");
        System.out.println(jdk1.hashCode());
        System.out.println(jdk2.hashCode());
        System.out.println();
        ctx.close();
    }
}
