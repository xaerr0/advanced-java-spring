package platform.codingnomads.co.springfundamentals.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StatusDemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatusDemoSpringBootApplication.class, args);
	}

}
