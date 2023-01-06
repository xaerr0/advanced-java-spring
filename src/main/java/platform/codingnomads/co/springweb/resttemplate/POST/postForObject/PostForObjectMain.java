package platform.codingnomads.co.springweb.resttemplate.POST.postForObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.POST.models.ResponseObject2;
import platform.codingnomads.co.springweb.resttemplate.POST.models.User;

@SpringBootApplication
public class PostForObjectMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForObjectMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
//            Task newTask = Task.builder()
//                    .name("learn how to use postForObject() - video demo")
//                    .description("get comfortable using the RestTemplate postForObject() method")
//                    //use a valid user id
//                    .userId(380)
//                    .completed(false)
//                    .build();
//
//            ResponseObject taskReturnedByServerAfterPost = restTemplate
//                    .postForObject("http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);
//
//            if (taskReturnedByServerAfterPost != null) {
//                System.out.println(taskReturnedByServerAfterPost.toString());
//            }




            User newUser1 = User.builder()
                    .email("billyjenkins@hotmail22.com")
                    .firstName("Billy")
                    .lastName("Jenkins")
                    .build();

            User newUser2 = User.builder()
                    .email("mrsandman@hotmail2.com")
                    .firstName("Meister")
                    .lastName("Sangman")
                    .build();

            User newUser3 = User.builder()
                    .email("PuPu@geocities.com2")
                    .firstName("PuPu")
                    .lastName("Stank")
                    .build();




            ResponseObject2 userReturnedByServerAfterPost = restTemplate
                    .postForObject("http://demo.codingnomads.co:8080/tasks_api/users",
                            newUser1, ResponseObject2.class);

            ResponseObject2 userReturnedByServerAfterPost2 = restTemplate
                    .postForObject("http://demo.codingnomads.co:8080/tasks_api/users",
                            newUser2, ResponseObject2.class);

            ResponseObject2 userReturnedByServerAfterPost3 = restTemplate
                    .postForObject("http://demo.codingnomads.co:8080/tasks_api/users",
                            newUser3, ResponseObject2.class);


            if (userReturnedByServerAfterPost != null) {
                System.out.println(userReturnedByServerAfterPost.toString());
            }

            if (userReturnedByServerAfterPost2 != null) {
                System.out.println(userReturnedByServerAfterPost2.toString());
            }

            if (userReturnedByServerAfterPost3 != null) {
                System.out.println(userReturnedByServerAfterPost3.toString());
            }




        };
    }
}