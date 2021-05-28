package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class CodeWarrior {
    private Address address;

    @Autowired
    public CodeWarrior(Address address) {
        this.address = address;
    }


}
