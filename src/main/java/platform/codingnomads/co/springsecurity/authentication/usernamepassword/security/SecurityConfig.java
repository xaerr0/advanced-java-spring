package platform.codingnomads.co.springsecurity.authentication.usernamepassword.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.models.Authority;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.models.RoleEnum;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.models.UserPrincipal;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //CSS should always be accessible for all
        return (web) -> web.ignoring().antMatchers("/css/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth ->
                        auth.antMatchers("/").authenticated())

                //formLogin() is used to indicate an HTML form is going to be used to present a username and password.
                // It also adds the UsernamePasswordAuthenticationFilter to the filter chain
                .formLogin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //the following beans are commented out to avoid conflict with CustomUserDetailsService
    //comment out the @Service annotation inside CustomUserDetailsService before uncommenting either of these

//    @Bean
//    public UserDetailsManager jdbcUserDetails(DataSource dataSource) {
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.setAuthoritiesByUsernameQuery("SELECT a.id, a.authority FROM authorities a \n" +
//                "JOIN user_authority_join_table uajt ON a.id = uajt.authority_id \n" +
//                "JOIN users u ON u.id = uajt.user_id \n" +
//                "WHERE u.username = ?");
//        users.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
//        return users;
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        Authority userAuth = Authority.builder().authority(RoleEnum.ROLE_USER).build();
//        UserPrincipal user1 = new UserPrincipal("USER1", passwordEncoder()
//                .encode("hi"), Collections.singletonList(userAuth));
//        UserPrincipal user2 = new UserPrincipal("USER2", passwordEncoder()
//                .encode("hello"), Collections.singletonList(userAuth));
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}