package platform.codingnomads.co.springdata.example.dml.lifecyclecallback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PrintEntity {

    @Id
    @GeneratedValue
    private Long id;

    // write your methods here

    @Transient
    private String greeting;

    @Transient
    private String cheeseWarning;

    @Transient
    private String brighterSide;

    public PrintEntity(String greeting) {
        this.greeting = greeting;
    }

    @PreUpdate
    private void greeting() {
        System.out.println("Good Afternoon Number:" + id);
    }

    @PrePersist
    private void cheeseWarning() {
        System.out.println("Remember, don't eat cheese after midnight!");

    }

    @PostLoad
    private void brighterSide() {
        System.out.println("The gas is always leaner on the utter side!");
    }
}