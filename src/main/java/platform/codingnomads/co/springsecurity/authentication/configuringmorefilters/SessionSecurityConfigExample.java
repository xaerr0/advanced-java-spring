package platform.codingnomads.co.springsecurity.authentication.configuringmorefilters;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration is normally required for your SecurityFilterChain to be registered
//it is commented out here only so it does not interfere with other example packages
//@Configuration
@EnableWebSecurity
public class SessionSecurityConfigExample {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //start an oath2 login system and:
                //change the success URL to /homepage
                //change the location of the login page and indicate you will be providing your own page
                .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl("/homepage")
                        .loginPage("/signin"))
                //indicate you are going to change the Session filters and:
                //set the maximum allowed sessions to 3
                //prevent a new login if the maximum number of sessions for a user has been reached
                //forward requests made using an expired JSESSIONID to /signin?expired
                .sessionManagement(session -> session
                        .maximumSessions(3)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/signin?expired"));

        return http.build();
    }
}