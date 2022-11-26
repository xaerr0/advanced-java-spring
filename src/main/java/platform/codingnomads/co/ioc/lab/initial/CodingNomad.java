package platform.codingnomads.co.ioc.lab.initial;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class CodingNomad {
    // constructor injection
    private final JDK jdk;
    private final IDE ide;
    private final Framework framework;

    private Keyboard keyboard;

    // setter injection
    @Autowired
    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    // field injection
    @Autowired
    Monitor monitor;

    public String createAwesomeSoftware() {
        return MessageFormat
                .format("This Coding Nomad is creating awesome software using, " +
                        "IDE: ({0}:{1}), JDK: ({2}:{3}), Framework: ({4}:{5}), Keyboard: ({6}:{7}) Monitor: ({8}:{9}),",
                        ide.getName(),
                        ide.getVersion(),
                        jdk.getName(),
                        jdk.getVersion(),
                        framework.getName(),
                        framework.getVersion(),
                        keyboard.getKeys(),
                        keyboard.getType(),
                        monitor.getCompany(),
                        monitor.getModel()

                );
    }
}