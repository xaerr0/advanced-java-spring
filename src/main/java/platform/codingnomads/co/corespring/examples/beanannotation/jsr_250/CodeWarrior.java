package platform.codingnomads.co.corespring.examples.beanannotation.jsr_250;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class CodeWarrior {
    public CodeWarrior() {
        System.out.println("----Code warrior is preparing for battle------");
    }

    @PostConstruct
    public void init() {
        System.out.println("Gathering all the resources for battle...");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up all resources....");
    }
}
