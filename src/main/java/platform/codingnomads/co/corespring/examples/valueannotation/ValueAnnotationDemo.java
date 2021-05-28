package platform.codingnomads.co.corespring.examples.valueannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ValueAnnotationDemo {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(ValueAnnotationDemo.class);
        final CodeWarrior codeWarrior = ctx.getBean(CodeWarrior.class);
        System.out.println(codeWarrior.getGreeting());
        final String output = codeWarrior.output();
        System.out.println(output);
        codeWarrior.getWorkingDays().forEach(System.out::println);
        System.out.println(codeWarrior.getDatabaseValues());
        System.out.println(codeWarrior.codeWarriorIdentity());
        ctx.close();
    }
}
