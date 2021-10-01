package platform.codingnomads.co.ioc.examples.constructorinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Laptop {
    private Processor processor;
    private OS os;

    @Autowired
    public Laptop(Processor processor, OS os) {
        this.processor = processor;
        this.os = os;
    }

    public String printLaptopConfiguration() {
        return "processor: " + processor.getCore() + " core " + processor.getName() +
                "\nOS: " + os.getName();
    }
}
