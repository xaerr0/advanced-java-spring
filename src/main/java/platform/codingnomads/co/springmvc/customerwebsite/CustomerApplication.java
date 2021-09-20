package platform.codingnomads.co.springmvc.customerwebsite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.domain.RentalCar;
import platform.codingnomads.co.springmvc.customerwebsite.service.CarService;
import platform.codingnomads.co.springmvc.customerwebsite.service.CustomerService;

import java.util.Arrays;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class);
    }

    @Bean
    public CommandLineRunner loadInitialData(CustomerService customerService, CarService carService) {
        return (args) -> {
            if (customerService.getCustomers().isEmpty()) {
                customerService.saveAllCustomer(Arrays.asList(
                        Customer.builder().fullName("Customer 1").emailAddress("customer1@gmail.com").address("Customer Address One").age(30).build(),
                        Customer.builder().fullName("Customer 2").emailAddress("customer2@gmail.com").address("Customer Address Two").age(28).build(),
                        Customer.builder().fullName("Customer 3").emailAddress("customer3@gmail.com").address("Customer Address Three").age(32).build()));
            }
            if (carService.getCars().isEmpty()) {
                carService.saveCar(RentalCar.builder().color("Gray").make("RAM").model("2500").license("5077W1X").build());
                carService.saveCar(RentalCar.builder().color("White").make("Volkswagen").model("Atlas").license("8B231T1").build());
            }
        };
    }
}
