package platform.codingnomads.co.aspectorientedprogramming.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import platform.codingnomads.co.aspectorientedprogramming.aop.model.Student;
import platform.codingnomads.co.aspectorientedprogramming.aop.service.StudentService;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class AOPDemo implements CommandLineRunner {

    private final StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(AOPDemo.class);
    }

    @Override
    public void run(String... args) throws Exception {

//        studentService.saveAllStudents(Arrays.asList(
//                Student.builder().email("student1@example.com").name("student1").build(),
//                Student.builder().email("student2@example.com").name("student2").build(),
//                Student.builder().email("student3@example.com").name("student3").build()
//        ));
//
//        studentService.fetchAllStudents();
        Student student1, student2, student3;

        student1 = Student.builder().email("test1@test.com").name("test 1").build();
        student2 = Student.builder().email("test2@test.com").name("test 2").build();
        student3 = Student.builder().email("test3@test.com").name("test 3").build();
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        studentService.saveStudent(student3);
    }


}