package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.codewarriorexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CodeWarriorApplication implements CommandLineRunner {

    @Autowired
    EmailAddressRepo emailAddressRepo;

    @Autowired
    CodeWarriorRepo codeWarriorRepo;

    public static void main(String[] args) {
        SpringApplication.run(CodeWarriorApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        final EmailAddress java_ninja = EmailAddress.builder().emailAddress("java@ninja.com").build();
        final EmailAddress java_guru = EmailAddress.builder().emailAddress("java@guru.com").build();
        final EmailAddress spring_guru = EmailAddress.builder().emailAddress("spring@guru.com").build();
        final EmailAddress spring_ninja = EmailAddress.builder().emailAddress("spring@ninja.com").build();

        final List<EmailAddress> emailAddresses = Arrays.asList(java_ninja, java_guru, spring_guru, spring_ninja);

        if (emailAddressRepo.findAll().isEmpty()) {
            emailAddressRepo.saveAll(emailAddresses);
        }

        final List<CodeWarrior> codeWarriors = Arrays.asList(
                CodeWarrior.builder().firstName("Java").lastName("Ninja").emailAddress(java_ninja).active(true).age(20).build(),
                CodeWarrior.builder().firstName("Java").lastName("Guru").emailAddress(java_guru).active(false).age(50).build(),
                CodeWarrior.builder().firstName("Spring").lastName("Guru").emailAddress(spring_guru).active(true).age(35).build(),
                CodeWarrior.builder().firstName("Spring").lastName("Ninja").emailAddress(spring_ninja).active(true).age(22).build(),
                CodeWarrior.builder().firstName("Spring").lastName("Ninja").emailAddress(spring_ninja).active(true).age(28).build(),
                CodeWarrior.builder().firstName("Mr.").lastName("Java").emailAddress(spring_ninja).active(true).age(43).build()
        );
        if (codeWarriorRepo.findAll().isEmpty()) {
            codeWarriorRepo.saveAll(codeWarriors);
        }

        final EmailAddress emailAddress = emailAddressRepo.findByEmailAddress("java@ninja.com");

        System.out.println("***************************************************findByEmailAddressAndLastName***************************************************");
        final CodeWarrior byEmailAddressAndLastName = codeWarriorRepo.findByEmailAddressAndLastName(emailAddress, "Ninja");
        System.out.println(byEmailAddressAndLastName);


        System.out.println("***************************************************findTop2ByLastName***************************************************");
        final List<CodeWarrior> top2ByLastName = codeWarriorRepo.findTop2ByLastName("Ninja");
        top2ByLastName.forEach(System.out::println);


        System.out.println("***************************************************findByLastNameIgnoreCase***************************************************");
        final List<CodeWarrior> byLastNameIgnoreCase = codeWarriorRepo.findByLastNameIgnoreCase("gUrU");
        byLastNameIgnoreCase.forEach(System.out::println);


        System.out.println("***************************************************findByLastNameAndFirstNameAllIgnoreCase***************************************************");
        final List<CodeWarrior> byLastNameAndFirstNameAllIgnoreCase = codeWarriorRepo.findByLastNameAndFirstNameAllIgnoreCase("gUrU", "jAvA");
        byLastNameAndFirstNameAllIgnoreCase.forEach(System.out::println);


        System.out.println("***************************************************findByLastNameOrderByFirstNameAsc***************************************************");
        final List<CodeWarrior> byLastNameOrderByFirstNameAsc = codeWarriorRepo.findByLastNameOrderByFirstNameAsc("Guru");
        byLastNameOrderByFirstNameAsc.forEach(System.out::println);


        System.out.println("***************************************************findByLastNameOrderByFirstNameDesc***************************************************");
        final List<CodeWarrior> byLastNameOrderByFirstNameDesc = codeWarriorRepo.findByLastNameOrderByFirstNameDesc("java");
        byLastNameOrderByFirstNameDesc.forEach(System.out::println);


        System.out.println("***************************************************findByEmailAddress_EmailAddress***************************************************");
        final CodeWarrior byEmailAddress_emailAddress = codeWarriorRepo.findByEmailAddress_EmailAddress("java@ninja.com");
        System.out.println(byEmailAddress_emailAddress);

    }
}
