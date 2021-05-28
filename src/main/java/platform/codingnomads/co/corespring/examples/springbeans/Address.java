package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Address {
    private String street;
    private Integer streetNumber;

    public Address(String street, int streetNumber) {
        this.street = street;
        this.streetNumber = streetNumber;
    }
}
