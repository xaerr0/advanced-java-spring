package platform.codingnomads.co.corespring.examples.profileannotation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("deploy")
public class Coffee {
    public Coffee() {
        System.out.println("Coffee is preparing");
    }
}