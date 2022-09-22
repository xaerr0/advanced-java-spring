package platform.codingnomads.co.springsecurity.authentication.configuringmorefilters;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration is normally required for your SecurityFilterChain to be registered
//it is commented out here only so it does not interfere with other example packages
//@Configuration
@EnableWebSecurity
public class ChannelSecurityConfigExample {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //start channel management and:
                //tell the ChannelProcessingFilter that all URLs starting with /insecure must be made over HTTP
                //tell the filter that all other urls need to be made over HTTPS
                .requiresChannel(channel -> channel
                        .antMatchers("/insecure/**")
                        .requiresInsecure()
                        .anyRequest()
                        .requiresSecure());

        return http.build();
    }
}
