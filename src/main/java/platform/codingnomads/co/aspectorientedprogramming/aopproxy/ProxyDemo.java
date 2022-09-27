package platform.codingnomads.co.aspectorientedprogramming.aopproxy;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import platform.codingnomads.co.aspectorientedprogramming.aopproxy.models.Person;
import platform.codingnomads.co.aspectorientedprogramming.aopproxy.models.PersonImpl;
import platform.codingnomads.co.aspectorientedprogramming.aopproxy.services.PersonService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ProxyDemo implements CommandLineRunner {

    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(ProxyDemo.class);
    }

    @Override
    public void run(String... args) throws Exception {

        // Create a ProxyFactory for the Person Class
        Person proxyPerson = new PersonImpl();
        ProxyFactory factoryOfPersons = new ProxyFactory(proxyPerson);
        factoryOfPersons.addInterface(Person.class);

        // Use the factory to get a proxy for the Person object
        proxyPerson = (Person) factoryOfPersons.getProxy();

        proxyPerson.setFirstName("Jane");
        proxyPerson.setLastName("Doe");
        System.out.println("Object class: " + proxyPerson.getClass().getCanonicalName());
        System.out.println("Full Name: " + proxyPerson.getFullName());

        Person person = new PersonImpl();

        person.setFirstName("John");
        person.setLastName("Doe");
        System.out.println("Object class: " + person.getClass().getCanonicalName());
        System.out.println("Full Name: " + personService.getPersonFullName(person));
    }
}
