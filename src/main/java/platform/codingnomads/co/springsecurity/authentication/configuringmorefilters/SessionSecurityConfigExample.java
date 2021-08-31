package platform.codingnomads.co.springsecurity.authentication.configuringmorefilters;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SessionSecurityConfigExample extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                //start an oath2 login system
                .oauth2Login()
                    //change the success URL to /homepage
                    .defaultSuccessUrl("/homepage")
                    //change the location of the login page and indicate you will be providing your own page
                    .loginPage("/signin")
                    //call and() to finish formLogin() customizations and get back to the methods available under http
                .and()

                .sessionManagement()
                    .maximumSessions(3)
                    .maxSessionsPreventsLogin(true)
                    .expiredUrl("/signin?expired");

    }

}
