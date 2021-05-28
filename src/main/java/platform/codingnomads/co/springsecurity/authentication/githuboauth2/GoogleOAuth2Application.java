package platform.codingnomads.co.springsecurity.authentication.githuboauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GoogleOAuth2Application {
    public static void main(String[] args) {
        SpringApplication.run(GoogleOAuth2Application.class);
    }
}

