package platform.codingnomads.co.aspectorientedprogramming.lab.service;

import org.springframework.stereotype.Service;
import platform.codingnomads.co.aspectorientedprogramming.lab.aspect.Printable;

@Service
public class GreetingService {

    public String greeting() {
        return "Hello Spring Developer!";
    }

    @Printable
    public String spanishGreeting() {
        return "Hola Desarrollador de Primavera!";
    }
}