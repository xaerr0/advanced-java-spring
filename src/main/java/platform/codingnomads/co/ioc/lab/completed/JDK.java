package platform.codingnomads.co.ioc.lab.completed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class JDK {
    private String name;
    private String version;
}
