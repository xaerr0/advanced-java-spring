package platform.codingnomads.co.springdata.example.springdatajdbc;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String firstName;
    private String lastName;

    public Client(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}