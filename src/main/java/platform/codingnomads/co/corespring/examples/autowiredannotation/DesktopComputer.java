package platform.codingnomads.co.corespring.examples.autowiredannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DesktopComputer {
    @Autowired
    @Qualifier("radeon")
    private VideoCard videoCard;
}
