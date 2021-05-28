package platform.codingnomads.co.corespring.examples.valueannotation;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CodeWarrior {

    private String name;

    private Integer age;

    public CodeWarrior(@Value("${codewarrior.name}") String name, @Value("${codewarrior.age}") Integer age) {
        this.name = name;
        this.age = age;
    }

    @Value("Code Warrior saying hello....!!")
    private String greeting;

    @Value("${codewarrior.framework}")
    private String framework;

    @Value("${codewarrior.jdk}")
    private String jdk;


    @Value("${codewarrior.ide:Intellj Idea}")
    private String ide;


    @Value("${codewarrior.workingDays}")
    private List<String> workingDays;


    @Value("#{${database.values}}")
    private Map<String, String> databaseValues;


    public String codeWarriorIdentity() {
        return name.concat(" ").concat(age.toString());
    }

    public String getGreeting() {
        return greeting;
    }

    public String output() {
        return "Code Warrior is building awesome software using: ".concat(jdk).concat(" , ").concat(framework).concat(" and ").concat(ide);
    }

    public List<String> getWorkingDays() {
        return workingDays;
    }

    public Map<String, String> getDatabaseValues() {
        return databaseValues;
    }
}
