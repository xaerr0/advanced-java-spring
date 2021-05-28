package platform.codingnomads.co.ioc.examples.constructorinjection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Processor {
    private Integer core;
    private String name;
}
