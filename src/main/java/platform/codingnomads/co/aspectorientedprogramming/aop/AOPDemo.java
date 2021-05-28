package platform.codingnomads.co.aspectorientedprogramming.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.aspectorientedprogramming.aop.domain.Student;
import platform.codingnomads.co.aspectorientedprogramming.aop.service.StudentService;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class AOPDemo implements CommandLineRunner {
    private final StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(AOPDemo.class);
    }

    @Override
    public void run(String... args) throws Exception {
        studentService.saveAllStudents(Arrays.asList(
                Student.builder().email("student1@example.com").name("student1").build(),
                Student.builder().email("student2@example.com").name("student2").build(),
                Student.builder().email("student3@example.com").name("student3").build()
        ));
        studentService.fetchAllStudents();
    }
}
