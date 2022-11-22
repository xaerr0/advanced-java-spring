package platform.codingnomads.co.ioc.examples.setterinjection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CPU {
    private String microprocessor;
    private String compMemory;
}