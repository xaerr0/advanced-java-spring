package platform.codingnomads.co.corespring.examples.autowiredannotation;


import lombok.ToString;
import org.springframework.stereotype.Component;

@Component("asus")
@ToString
public class Asus implements Monitor{
}