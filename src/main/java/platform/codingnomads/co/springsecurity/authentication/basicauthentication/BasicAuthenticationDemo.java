package platform.codingnomads.co.springsecurity.authentication.basicauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.Authority;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.RoleEnum;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.UserPrincipal;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.repositories.AuthorityRepo;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.repositories.UserPrincipalRepo;

import java.util.Collections;

@SpringBootApplication
public class BasicAuthenticationDemo implements CommandLineRunner {

    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private UserPrincipalRepo userPrincipalRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BasicAuthenticationDemo.class);
    }

    @Override
    public void run(String... args) throws Exception {

        Authority userAuth = Authority.builder().authority(RoleEnum.ROLE_USER).build();
        if (authorityRepo.findAll().isEmpty()) {
            authorityRepo.save(userAuth);
        }

        if (userPrincipalRepo.findAll().isEmpty()) {
            userPrincipalRepo.save(
                    new UserPrincipal("USER", passwordEncoder.encode("user"),
                            Collections.singletonList(userAuth))
            );
        }
    }
}

