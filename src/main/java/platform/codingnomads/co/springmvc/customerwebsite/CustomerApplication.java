package platform.codingnomads.co.springmvc.customerwebsite;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.service.CustomerService;

import javax.annotation.Nonnull;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class CustomerApplication implements CommandLineRunner {
    @Nonnull private final CustomerService customerService;
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        customerService.saveAllCustomer(Arrays.asList(
                Customer.builder().fullName("Customer 1").emailAddress("customer1@gmail.com").address("Customer Address One").age(30).build(),
                Customer.builder().fullName("Customer 2").emailAddress("customer2@gmail.com").address("Customer Address Two").age(28).build(),
                Customer.builder().fullName("Customer 3").emailAddress("customer3@gmail.com").address("Customer Address Three").age(32).build()
                )
        );

    }
}
