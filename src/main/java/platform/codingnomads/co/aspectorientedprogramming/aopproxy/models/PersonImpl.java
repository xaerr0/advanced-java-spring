package platform.codingnomads.co.aspectorientedprogramming.aopproxy.models;

import lombok.Data;

@Data
public class PersonImpl implements Person {

    private String firstName;
    private String lastName;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
