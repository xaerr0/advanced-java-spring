package platform.codingnomads.co.springsecurity.authentication.configuringmorefilters;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ChannelSecurityConfigExample extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //start channel management
                .requiresChannel()
                //tell the ChannelProcessingFilter that all URLs starting with /insecure must be made over HTTP
                .antMatchers("/insecure/**").requiresInsecure()
                //tell the filter that all other urls need to be made over HTTPS
                .anyRequest().requiresSecure();
    }
}
