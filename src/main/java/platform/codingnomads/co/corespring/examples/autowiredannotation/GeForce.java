package platform.codingnomads.co.corespring.examples.autowiredannotation;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component("geforce")
@ToString
public class GeForce implements VideoCard {
}
