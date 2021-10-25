package platform.codingnomads.co.springsecurity.authorization.custompermissions.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
//enable method security and make sure pre and post-authorization is enabled
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecConfig extends GlobalMethodSecurityConfiguration {

    //autowire in the CustomPermissionEvaluator that was registered as a bean using @Component
    @Autowired
    CustomPermissionEvaluator customPermissionEvaluator;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        //create a new DefaultMethodSecurityExpressionHandler to add the PermissionEvaluator to
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        //add the PermissionEvaluator
        handler.setPermissionEvaluator(customPermissionEvaluator);
        return handler;
    }
}