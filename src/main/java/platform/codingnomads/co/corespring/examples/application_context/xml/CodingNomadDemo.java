package platform.codingnomads.co.corespring.examples.application_context.xml;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class CodingNomadDemo {

    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("xml-config/codingnomad_configuration.xml");
        CodingNomad codingNomad = ctx.getBean(CodingNomad.class);
        System.out.println(codingNomad.createAwesomeSoftware());
    }
}

