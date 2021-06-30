package platform.codingnomads.co.corespring.examples.componentscanannotation.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import platform.codingnomads.co.corespring.examples.componentscanannotation.pc.MotherBoard;

@Component
public class Framework {
    @Bean
    public MotherBoard motherBoard() {
        return new MotherBoard();
    }
}

