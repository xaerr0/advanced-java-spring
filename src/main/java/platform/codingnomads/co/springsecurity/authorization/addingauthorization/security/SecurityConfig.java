package platform.codingnomads.co.springsecurity.authorization.addingauthorization.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.services.CustomUserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService customUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //disable CSRF filter to allow Postman to easily use the application
                .csrf().disable()

                //start changing endpoint access settings
                .authorizeRequests()
                    //the following 4 paths should be allowed to all always. They are static and are required to present the pages properly.
                    .antMatchers("/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                    //make sure that the admin page can only be accessed user with ROLE_ADMIN
                    .antMatchers("/admin").hasRole("ADMIN")
                    //only allow users with ROLE_SUPERU to access the super user page
                    .antMatchers("/superu").hasRole("SUPERU")
                    //only allow users with an UPDATER authority to update users.
                    .antMatchers("/update-user").hasAuthority("UPDATER")
                    //make sure that all others requests require authentication.
                    .anyRequest().authenticated()
                .and()

                //use a form to log in with the default login page
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //use the CustomUserService to retrieve user data
        auth.userDetailsService(customUserService)
                //register the BCryptPasswordEncoder with the AuthenticationManager
                .passwordEncoder(passwordEncoder());
    }
}