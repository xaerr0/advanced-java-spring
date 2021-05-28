package platform.codingnomads.co.ioc.lab.completed;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CodeWarriorDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                CodeWarriorConfiguration.class
        );
        CodeWarrior codeWarrior = ctx.getBean(CodeWarrior.class);
        System.out.println(codeWarrior.createAwesomeSoftware());
    }
}

