package platform.codingnomads.co.ioc.examples.setterinjection;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Harddrive {
    private String platter;
    private String actuator;
}