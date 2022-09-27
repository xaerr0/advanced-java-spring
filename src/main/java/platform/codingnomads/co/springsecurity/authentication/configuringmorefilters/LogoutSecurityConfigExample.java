package platform.codingnomads.co.springsecurity.authentication.configuringmorefilters;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration is normally required for your SecurityFilterChain to be registered
//it is commented out here only so it does not interfere with other example packages
//@Configuration
@EnableWebSecurity
public class LogoutSecurityConfigExample {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //indicate a form login should be used and also:
                //change the location of the login page and indicate you will be providing your own page
                //change the success URL to /homepage
                .formLogin(login -> login
                        .loginPage("/signin")
                        .defaultSuccessUrl("/homepage"))
                //indicate you are going to be customizing a logout filter and:
                //indicate the URL that will trigger a logout
                //change where a user will be redirected after the logout completes
                .logout(logout -> logout
                        .logoutUrl("/signout")
                        .logoutSuccessUrl("/signin"));

        return http.build();
    }
}
