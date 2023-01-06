package platform.codingnomads.co.springweb.resttemplate.POST.postForLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import platform.codingnomads.co.springweb.resttemplate.POST.models.ResponseObject;
import platform.codingnomads.co.springweb.resttemplate.POST.models.ResponseObject2;
import platform.codingnomads.co.springweb.resttemplate.POST.models.Task;
import platform.codingnomads.co.springweb.resttemplate.POST.models.User;

import java.net.URI;
import java.util.Objects;

@SpringBootApplication
public class PostForLocationMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForLocationMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForLocation()")
                    .description("get comfortable using the RestTemplate postForLocation() method")
                    //be sure to use a valid user id
                    .userId(380)
                    .completed(false)
                    .build();

            //use postForLocation() to get the URL for the new resource
            URI returnedLocation = restTemplate
                    .postForLocation("http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

            System.out.println(Objects.requireNonNull(returnedLocation));

            ResponseEntity<?> responseEntity = restTemplate
                    .postForEntity("http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

            System.out.println(responseEntity.getHeaders().get("Location"));



            User newUser1 = User.builder()
                    .email("tadpohl@angelcities2.com")
                    .first_name("Tad2")
                    .last_name("Pohl2")
                    .build();

//            URI returnedLocation2 = restTemplate.postForLocation("http://demo.codingnomads.co:8080/tasks_api/users",
//                    newUser1, ResponseObject2.class);
//            System.out.println(Objects.requireNonNull(returnedLocation2));

            ResponseEntity<?> responseEntity1 = restTemplate
                    .postForEntity("http://demo.codingnomads.co:8080/tasks_api/users", newUser1, ResponseObject2.class);
            System.out.println(responseEntity1.getHeaders().get("Location"));


            User newUser2 = User.builder()
                    .email("scupp@angelcities.com")
                    .first_name("Stanley")
                    .last_name("Cupp")
                    .build();

            URI returnedLocation2 = restTemplate.postForLocation("http://demo.codingnomads.co:8080/tasks_api/users",
                    newUser2, ResponseObject2.class);
            System.out.println(Objects.requireNonNull(returnedLocation2));

            User newUser3 = User.builder()
                    .email("rsprout@angelcities.com")
                    .first_name("Russell")
                    .last_name("Sprouts")
                    .build();

            URI returnedLocation3 = restTemplate.postForLocation("http://demo.codingnomads.co:8080/tasks_api/users",
                    newUser3, ResponseObject2.class);
            System.out.println(Objects.requireNonNull(returnedLocation3));
        };
    }
}