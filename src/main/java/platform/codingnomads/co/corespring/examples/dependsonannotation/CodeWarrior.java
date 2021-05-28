package platform.codingnomads.co.corespring.examples.dependsonannotation;

import org.springframework.beans.factory.annotation.Autowired;

public class CodeWarrior {
    @Autowired
    private JDK jdk;

    public CodeWarrior() {
        System.out.println("Code Warrior is preparing for code battle !!!");
    }
}
