package platform.codingnomads.co.corespring.examples.propertysourceannoation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Profile("test")
public class Computer {
    @Value("${computer.monitor}")
    private String monitor;

    @Value("${computer.keyboard}")
    private String keyboard;

    public String getMonitor() {
        return monitor;
    }

    public String getKeyboard() {
        return keyboard;
    }
}