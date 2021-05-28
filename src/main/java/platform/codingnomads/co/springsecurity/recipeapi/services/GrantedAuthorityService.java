package platform.codingnomads.co.springsecurity.recipeapi.services;

import org.springframework.stereotype.Service;
import platform.codingnomads.co.springsecurity.recipeapi.models.securitymodels.Role;

import java.util.ArrayList;

@Service
public class GrantedAuthorityService {

    public ArrayList<Role> getGrantedAuthoritiesByUserId(Long userId) {
        return new ArrayList<>();
    }
}
