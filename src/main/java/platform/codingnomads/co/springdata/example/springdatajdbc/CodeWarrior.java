
package platform.codingnomads.co.springdata.example.springdatajdbc;

import lombok.Data;

@Data
public class CodeWarrior {
    private long id;
    private String firstName, lastName;

    public CodeWarrior(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}