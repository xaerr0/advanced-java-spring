package platform.codingnomads.co.springsecurity.authentication.githuboauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String getIndexPage(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        System.out.println(authentication.toString());
        return "oauth2/index";
    }

}