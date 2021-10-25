package platform.codingnomads.co.springsecurity.authentication.basicauthentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.Authority;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.RoleEnum;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.UserPrincipal;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.service.CustomUserDetailsService;

import javax.sql.DataSource;
import java.util.Collections;


@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //start the creating process of authorization settings. This will be covered in depth in the authorization chapter.
                .authorizeRequests()
                //CSS should always be accessible for all clients
                .antMatchers("/css/**").hasRole("USER")
                .antMatchers("/css/**").permitAll()
                .antMatchers("/").authenticated()
                //any other request should be authenticated
                .anyRequest().permitAll()
                .and()

                //formLogin() is used to indicate an HTML form is going to be used to present a username and password.
                // It also adds the UsernamePasswordAuthenticationFilter to the filter chain
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

        Authority userAuth = Authority.builder().authority(RoleEnum.ROLE_USER).build();

        auth.inMemoryAuthentication()
                .withUser(new UserPrincipal("USER1", passwordEncoder().encode("hi"), Collections.singletonList(userAuth)))
                .withUser(new UserPrincipal("USER2", passwordEncoder().encode("hello"), Collections.singletonList(userAuth)));

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT a.id, a.authority FROM authorities a \n" +
                                            "JOIN user_authority_join_table uajt ON a.id = uajt.authority_id \n" +
                                            "JOIN users u ON u.id = uajt.user_id \n" +
                                            "WHERE u.username = ?")
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");

    }

}