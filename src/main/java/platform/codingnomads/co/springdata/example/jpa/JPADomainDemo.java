package platform.codingnomads.co.springdata.example.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import platform.codingnomads.co.springdata.example.jpa.domain.*;
import platform.codingnomads.co.springdata.example.jpa.repositories.CodeWarriorRepository;
import platform.codingnomads.co.springdata.example.jpa.repositories.UserDetailRepository;
import platform.codingnomads.co.springdata.example.jpa.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories("platform.codingnomads.co.springdata.example.jpa.repositories")
@RequiredArgsConstructor
public class JPADomainDemo implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final CodeWarriorRepository codeWarriorRepository;

    public static void main(String[] args) {
        SpringApplication.run(JPADomainDemo.class);
    }

    @Override
    public void run(String... args) throws Exception {

        /*One To One Demo*/
        userRepository.deleteAllInBatch();
        userDetailRepository.deleteAllInBatch();

        UserDetail userDetail = UserDetail.builder()
                .mobile("0123456789")
                .build();

        User user = User.builder()
                .name("Java Ninja")
                .build();

        // Set child reference(userDetail) in parent entity(user)

        user.setUserDetail(userDetail);

        // Set parent reference(user) in child entity(userDetail)

        userDetail.setUser(user);

        // Save Parent Reference (which will save the child as well)

        userRepository.save(user);

        userRepository.findAll().forEach(u -> System.out.println(u.getName()));


        /*Bidirectional One To Many */

        CodeWarrior codeWarrior = CodeWarrior.builder()
                .name("Java Ninja")
                .build();
        codeWarrior.addWeapon(Weapon.builder().name("JDK").build());
        codeWarrior.addWeapon(Weapon.builder().name("Spring").build());


        codeWarriorRepository.save(codeWarrior);
        codeWarriorRepository.findAll().forEach(cw -> codeWarrior.getWeapons()
                .forEach(item -> System.out.println(item.getName())));
    }
}