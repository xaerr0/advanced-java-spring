package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class SpringDeveloper {

    private Address address;
    private Meal meal;

    @Autowired
    public SpringDeveloper(Address address) {
        this.address = address;
    }

    @Autowired
    public SpringDeveloper(Meal meal) {
        this.meal = meal;
    }
}