package platform.codingnomads.co.springdata.example.moderndatabaseintegration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringDataJDBCExample implements CommandLineRunner {

    @Autowired
    private JdbcTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCExample.class);
    }

    @Override
    public void run(String... args) throws Exception {

        template.query("SELECT * FROM EMPLOYEES", new Object[]{},
            (rs, rowNum) -> new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
            )
        ).forEach(manager -> System.out.println(manager.toString()));
    }

    @Data
    @AllArgsConstructor
    private class Employee {
        private int id;
        private String firstName;
        private String lastName;
    }
}


