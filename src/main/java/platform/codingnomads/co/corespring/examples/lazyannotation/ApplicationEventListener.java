package platform.codingnomads.co.corespring.examples.lazyannotation;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {
    @EventListener(classes = {ContextStartedEvent.class})
    public void contextStartedEventListener() {
        System.out.println("Application started");
    }
}
