package platform.codingnomads.co.springsecurity.authentication.usernamepassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.models.Authority;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.models.RoleEnum;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.models.UserPrincipal;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.repositories.AuthorityRepo;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.repositories.UserPrincipalRepo;

import java.util.Collections;

@SpringBootApplication
public class AuthenticationDemo implements CommandLineRunner {

    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private UserPrincipalRepo userPrincipalRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationDemo.class);
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

