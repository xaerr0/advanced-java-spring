package platform.codingnomads.co.springsecurity.authorization.addingauthorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.models.Authority;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.models.AuthorityEnum;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.models.UserMeta;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.models.UserPrincipal;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.repositories.AuthorityRepo;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.repositories.UserPrincipalRepo;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class AuthorizationDemo implements CommandLineRunner {

    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private UserPrincipalRepo userPrincipalRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationDemo.class);
    }

    @Override
    public void run(String... args) throws Exception {

        Authority userAuth = Authority.builder().authority(AuthorityEnum.ROLE_USER).build();
        Authority adminAuth = Authority.builder().authority(AuthorityEnum.ROLE_ADMIN).build();
        Authority superUAuth = Authority.builder().authority(AuthorityEnum.ROLE_SUPERU).build();
        Authority updaterAuth = Authority.builder().authority(AuthorityEnum.UPDATER).build();

        if (authorityRepo.findAll().isEmpty()) {
            authorityRepo.saveAll(Arrays.asList(userAuth,adminAuth, superUAuth, updaterAuth));
        }

        UserMeta superUser = UserMeta.builder().name("super user").email("superuser@email.com").build();
        UserMeta admin = UserMeta.builder().name("admin").email("admin@email.com").build();
        UserMeta basicUser = UserMeta.builder().name("basic user").email("basicuser@email.com").build();

        if (userPrincipalRepo.findAll().isEmpty()) {
            userPrincipalRepo.saveAll(
                    Arrays.asList(
                            new UserPrincipal("SUPERUSER", passwordEncoder.encode("su"),
                                    Arrays.asList(userAuth, adminAuth, superUAuth, updaterAuth), superUser),
                            new UserPrincipal("USER", passwordEncoder.encode("user"),
                                    Collections.singletonList(userAuth), basicUser),
                            new UserPrincipal("ADMIN", passwordEncoder.encode("admin"),
                                    Arrays.asList(adminAuth, userAuth), admin)
                    )
            );
        }
    }
}

