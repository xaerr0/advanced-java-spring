package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//TODO How come AllArgs... doesn't work here but works in Address POJOO?
//@AllArgsConstructor
@Getter
@Setter
public class Meal {
    private String main;
    private String side;

    public Meal(String main, String side) {
        this.main = main;
        this.side = side;
    }
}