package platform.codingnomads.co.springsecurity.authorization.custompermissions.config;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();

        try {
            //Some reflective work to get the ID in question
            Class<?> targetType = targetDomainObject.getClass();
            Method method = targetType.getMethod("getId");
            Long id = (Long) method.invoke(targetDomainObject);

            //compile GrantedAuthorityString
            String grantedAuthorityString = id + "_" + targetType.getName() + "_" + permission;

            //check if user has matching authority. Return true if so false otherwise
            for (GrantedAuthority ga : grantedAuthorities) {
                if (ga.getAuthority().equalsIgnoreCase(grantedAuthorityString)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();

        try {
            //compile grantedAuthorityString
            String grantedAuthorityString = targetId + "_" + targetType + "_" + permission;

            //check if user has matching authority. Return true if so false otherwise
            for (GrantedAuthority ga : grantedAuthorities) {
                if (ga.getAuthority().equalsIgnoreCase(grantedAuthorityString)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
}