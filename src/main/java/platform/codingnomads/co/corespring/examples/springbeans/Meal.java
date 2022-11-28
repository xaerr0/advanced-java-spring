package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.Getter;
import lombok.Setter;

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