package platform.codingnomads.co.corespring.examples.profileannotation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class CodeWarrior {
    public CodeWarrior() {
        System.out.println("Code Warrior is preparing for battle....!!!");
    }
}
