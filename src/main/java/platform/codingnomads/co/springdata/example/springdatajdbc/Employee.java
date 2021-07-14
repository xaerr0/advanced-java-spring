package platform.codingnomads.co.springdata.example.springdatajdbc;

import lombok.Data;

@Data
public class Employee {
    private long id;
    private String firstName, lastName;

    public Employee(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}