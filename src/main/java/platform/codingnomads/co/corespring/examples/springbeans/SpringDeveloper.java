package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class SpringDeveloper {

    private Address address;
    private Meal meal;

    public SpringDeveloper(Address address, Meal meal) {
        this.address = address;
        this.meal = meal;
    }
}