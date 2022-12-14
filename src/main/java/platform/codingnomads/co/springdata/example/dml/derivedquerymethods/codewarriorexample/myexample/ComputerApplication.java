package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.codewarriorexample.myexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ComputerApplication implements CommandLineRunner {

    @Autowired
    ComputerRepo computerRepo;

    public static void main(String[] args) {
        SpringApplication.run(ComputerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        final List<Computer> computers = Arrays.asList(
                Computer.builder().monitor("Asus").mouse("Logitech").keyboard("Akko").forGaming(true).build(),
                Computer.builder().monitor("Logitech").mouse("Razer").keyboard("Logitech").forGaming(true).build(),
                Computer.builder().monitor("Acer").mouse("Asus").keyboard("Razer").forGaming(true).build(),
                Computer.builder().monitor("Samsung").mouse("Corsair").keyboard("Drop").forGaming(true).build(),
                Computer.builder().monitor("MSI").mouse("HyperX").keyboard("Ducky").forGaming(true).build(),
                Computer.builder().monitor("Dell").mouse("Logitech").keyboard("Razer").forGaming(true).build(),
                Computer.builder().monitor("Acer").mouse("HyperX").keyboard("Akko").forGaming(false).build(),
                Computer.builder().monitor("Logitech").mouse("Corsair").keyboard("Logitech").forGaming(false).build()
        );

        if (computerRepo.findAll().isEmpty()) {
            computerRepo.saveAll(computers);
        }


        System.out.println("----------Find By Monitor Name (Logitech)----------");
        final List<Computer> findByMonitor = computerRepo.findByMonitor("Logitech");
        findByMonitor.forEach(System.out::println);

        System.out.println("----------Find Computers That Are For Gaming----------");
        final List<Computer> findByForGamingIsTrue = computerRepo.findByForGamingIsTrue();
        findByForGamingIsTrue.forEach(System.out::println);

        System.out.println("----------Find First Mouse With 'Corsair'----------");
        final Computer findFirstByMouse = computerRepo.findFirstByMouse("Corsair");
        System.out.println(findFirstByMouse);

        System.out.println("----------Find Keyboard Containing 'Ak' AND if TRUE For Gaming----------");
        final List<Computer> findByKeyboardContainingAndForGaming = computerRepo.findByKeyboardContainingAndForGaming(
                "Ak", true);
        findByKeyboardContainingAndForGaming.forEach(System.out::println);

        System.out.println("----------Find Keyboards NOT Containing 'Akko'----------");
        final List<Computer> findByKeyboardIsNot = computerRepo.findByKeyboardIsNot("Akko");
        findByKeyboardIsNot.forEach(System.out::println);

        System.out.println("----------Find Mice? Starting With 'a'----------");
        final List<Computer> findByMouseStartingWith = computerRepo.findByMouseStartingWith("a");
        findByMouseStartingWith.forEach(System.out::println);

        System.out.println("----------Find Monitor Ending With 'r'----------");
        final List<Computer> findByMonitorEndingWith = computerRepo.findByMonitorEndingWith("r");
        findByMonitorEndingWith.forEach(System.out::println);

    }

}