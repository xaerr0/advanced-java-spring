package platform.codingnomads.co.springsecurity.recipeapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import platform.codingnomads.co.springsecurity.recipeapi.services.userservices.CustomUserDetailService;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //disable CSRF for Postman usage
                .csrf().disable()
                //authorize all requests to access CSS and JavaScript
                .authorizeRequests()
                    .antMatchers("/css", "/js").permitAll()
                //allow all requests to read recipes and reviews
                .antMatchers(HttpMethod.GET, "/recipes/**", "/reviews").permitAll()
                //allow creation of new recipes and reviews
                .antMatchers(HttpMethod.POST, "/recipes", "reviews").permitAll()
                //all other requests should be authenticated
                .anyRequest().authenticated()
                .and()

                //users should login with a form. Default Spring login page is used.
                .formLogin()
                //forward to /current-user for a summary of the signed in user.
                .defaultSuccessUrl("/current-user");
    }

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //use a customUserDetailService and a BCryptPasswordEncoder to set up an AuthenticationManager.
        //more on these two classes soon
         auth.userDetailsService(customUserDetailService).passwordEncoder(encoder);
    }
}
