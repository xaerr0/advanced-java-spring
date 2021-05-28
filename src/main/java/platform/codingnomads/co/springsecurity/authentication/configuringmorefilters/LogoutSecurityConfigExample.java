package platform.codingnomads.co.springsecurity.authentication.configuringmorefilters;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class LogoutSecurityConfigExample extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //indicate a form login should be used
                .formLogin()
                    //change the success URL to /homepage
                    .defaultSuccessUrl("/homepage")
                    //change the location of the login page and indicate you will be providing your own page
                    .loginPage("/signin")
                //call and() to finish formLogin() customizations and get back to the methods available under http
                .and()

                //indicate you are going to be customizing a logout filter
                .logout()
                    //indicate the URL that will trigger a logout
                    .logoutUrl("/signout")
                    //change where a user will be redirected after the logout completes
                    .logoutSuccessUrl("/signin");
    }

}
