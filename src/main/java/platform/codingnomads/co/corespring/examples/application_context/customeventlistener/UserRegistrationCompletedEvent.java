package platform.codingnomads.co.corespring.examples.application_context.customeventlistener;

import org.springframework.context.ApplicationEvent;

public class UserRegistrationCompletedEvent extends ApplicationEvent {

    private final String message;

    public UserRegistrationCompletedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
