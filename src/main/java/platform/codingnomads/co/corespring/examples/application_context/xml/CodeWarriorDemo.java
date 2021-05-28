package platform.codingnomads.co.corespring.examples.application_context.xml;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class CodeWarriorDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("xml-config/code_warrior_configuration.xml");
        CodeWarrior codeWarrior = ctx.getBean(CodeWarrior.class);
        System.out.println(codeWarrior.createAwesomeSoftware());
    }
}

