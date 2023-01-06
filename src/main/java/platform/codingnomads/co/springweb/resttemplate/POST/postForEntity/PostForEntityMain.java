package platform.codingnomads.co.springweb.resttemplate.POST.postForEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.POST.models.ResponseObject;
import platform.codingnomads.co.springweb.resttemplate.POST.models.ResponseObject2;
import platform.codingnomads.co.springweb.resttemplate.POST.models.Task;
import platform.codingnomads.co.springweb.resttemplate.POST.models.User;

import java.util.Objects;

@SpringBootApplication
public class PostForEntityMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForEntityMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForEntity()")
                    .description("get comfortable using the RestTemplate postForEntity() method")
                    //be sure to use valid user id
                    .userId(380)
                    .completed(false)
                    .build();

            ResponseEntity<ResponseObject> responseEntity = restTemplate
                    .postForEntity("http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                System.out.println(Objects.requireNonNull(responseEntity.getBody()));
            } else {
                System.out.println(Objects.requireNonNull(responseEntity.getBody()).getError());
            }


            User newUser1 = User.builder()
                    .email("FChild@geocities.com")
                    .first_name("Foster")
                    .last_name("Child")
                    .build();

            ResponseEntity<ResponseObject2> responseEntity1 = restTemplate.postForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api/users", newUser1, ResponseObject2.class);

            if (responseEntity1.getStatusCode().equals(HttpStatus.CREATED)) {
                System.out.println(Objects.requireNonNull(responseEntity1.getBody()));
            } else {
                System.out.println(Objects.requireNonNull(responseEntity1.getBody().getError()));
            }

            User newUser2 = User.builder()
                    .email("Joking@angelfire.com")
                    .first_name("Jo")
                    .last_name("King")
                    .build();
            ResponseEntity<ResponseObject2> responseEntity2 = restTemplate.postForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api/users", newUser2, ResponseObject2.class);

            if (responseEntity2.getStatusCode().equals(HttpStatus.CREATED)) {
                System.out.println(Objects.requireNonNull(responseEntity2.getBody()));
            } else {
                System.out.println(Objects.requireNonNull(responseEntity2.getBody().getError()));
            }


        };
    }
}