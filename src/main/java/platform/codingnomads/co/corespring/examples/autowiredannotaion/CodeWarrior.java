package platform.codingnomads.co.corespring.examples.autowiredannotaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CodeWarrior {
    @Autowired
    @Qualifier("jdk")
    private Weapon weapon;
}
