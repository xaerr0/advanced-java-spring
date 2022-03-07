package platform.codingnomads.co.corespring.examples.application_context.frameworkevents;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

// @Component tells Spring that this is a bean it should register
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("ContextRefreshedEvent has fired!!");
    }
}