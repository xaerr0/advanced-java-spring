package platform.codingnomads.co.springdata.example.springdatajdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringDataJDBCDemo implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCDemo.class);
    }

    @Override
    public void run(String... strings) {

//        try {
//            //create employee table using the JdbcTemplate method "execute"
//            jdbcTemplate.execute("CREATE TABLE employees (id BIGINT AUTO_INCREMENT PRIMARY KEY," +
//                    "first_name VARCHAR(255) NOT NULL,last_name  VARCHAR(255) NOT NULL);");
//        } catch (Exception e) {
//            //nothing
//        }

        try {
            //create client table using the JdbcTemplate method "execute"
            jdbcTemplate.execute("CREATE TABLE clients (id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                                 "first_name VARCHAR(255) NOT NULL,last_name  VARCHAR(255) NOT NULL);");
        } catch (Exception e) {
            //nothing
        }

        //create a list of first and last names for employee
//        List<Object[]> splitUpNames = Stream.of("Java Ninja", "Spring Guru", "Java Guru", "Spring Ninja")
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());

        //create a list of first and last names for client
        List<Object[]> splitUpNames = Stream.of("Bob Higgins", "Radolfus Sanchez", "Betsy McNubergobbin", "Debbie Spartica")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        //for each first & last name pair insert an Employee into the database
//        for (Object[] name : splitUpNames) {
//            jdbcTemplate.execute(String.format("INSERT INTO employees(first_name, last_name) VALUES ('%s','%s')", name[0], name[1]));
//        }

        //for each first & last name pair insert a Client into the database
        for (Object[] name : splitUpNames) {
            jdbcTemplate.execute(String.format("INSERT INTO clients(first_name, last_name) VALUES ('%s','%s')", name[0], name[1]));
        }

        //query the database for Employees with first name Java
//        jdbcTemplate.query(
//                        "SELECT id, first_name, last_name FROM employees WHERE first_name = 'Java'",
//                        (rs, rowNum) -> new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//                )
//                //print each found employee to the console
//                .forEach(employee -> System.out.println(employee.toString()));

        //query the database for all Clients
        jdbcTemplate.query(
                        "SELECT id, first_name, last_name FROM clients",
                        (rs, rowNum) -> new Client(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
                )
                //print each found employee to the console
                .forEach(client -> System.out.println(client.toString()));
        //truncate the table
//        jdbcTemplate.execute("TRUNCATE TABLE employees;");
//        //delete the table
//        jdbcTemplate.execute("DROP TABLE employees");

        //truncate the table
        jdbcTemplate.execute("TRUNCATE TABLE clients;");
        //delete the table
        jdbcTemplate.execute("DROP TABLE clients");
    }
}