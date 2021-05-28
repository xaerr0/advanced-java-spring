package platform.codingnomads.co.springsecurity.authentication.basicauthentication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.UserPrincipal;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.repositories.UserPrincipalRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserPrincipalRepo userPrincipalRepo;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
    }
}
