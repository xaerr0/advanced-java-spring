package platform.codingnomads.co.springdata.example.springdatajdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataJDBCDemo implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCDemo.class);
    }

    @Override
    public void run(String... strings) {

        try {
            //create code warrior table using the execute JdbcTemplate method
            jdbcTemplate.execute("CREATE TABLE code_warriors (id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(255) NOT NULL,last_name  VARCHAR(255) NOT NULL);");
        }catch (Exception e) {
            //nothing
        }

        //create a list of first and last names
        List<Object[]> splitUpNames = Stream.of("Java Ninja", "Spring Guru", "Spring Ninja")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        //for each first & last name pair insert a CodeWarrior into the database
        for(Object[] name: splitUpNames) {
            jdbcTemplate.execute(String.format("INSERT INTO code_warriors(first_name, last_name) VALUES ('%s','%s')", name[0], name[1]));
        }

        //query the database for CodeWarriors with first name Java
        CodeWarrior codeWarrior = jdbcTemplate.queryForObject(
                "SELECT id, first_name, last_name FROM code_warriors WHERE first_name = 'Java' ",
                CodeWarrior.class
                );
                //print each found CodeWarrior to the console

        assert codeWarrior != null;
        System.out.println(codeWarrior.toString());

        //truncate the table
        jdbcTemplate.execute("TRUNCATE TABLE code_warriors;");
        //delete the table
        jdbcTemplate.execute("DROP TABLE code_warriors");
    }

}
