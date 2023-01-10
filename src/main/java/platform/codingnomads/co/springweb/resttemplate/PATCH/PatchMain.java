package platform.codingnomads.co.springweb.resttemplate.PATCH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.PATCH.models.ResponseObject;
import platform.codingnomads.co.springweb.resttemplate.PATCH.models.ResponseObject2;
import platform.codingnomads.co.springweb.resttemplate.PATCH.models.Task;
import platform.codingnomads.co.springweb.resttemplate.PATCH.models.User;

import java.util.Objects;

@SpringBootApplication
public class PatchMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PatchMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            //create an empty Task
            Task task = new Task();

            //be sure to use a valid task id
            task.setId(169);

            //set fields you want to change. All other fields are null and will not be updated
            task.setName("use patchForObject()");
            task.setDescription("this task was updated using patchForObject()");

            //send the PATCH request using the URL, the Task created above and the ResponseObject Class
            ResponseObject objectResponse = restTemplate
                    .patchForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(), task, ResponseObject.class);

            System.out.println(Objects.requireNonNull(objectResponse));

            task.setName("PATCH using exchange()");
            task.setDescription("This task was updated using PATCH");

            HttpEntity<Task> httpEntity = new HttpEntity<>(task);
            ResponseEntity<ResponseObject> response = restTemplate
                    .exchange("http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(), HttpMethod.PATCH, httpEntity, ResponseObject.class);

            System.out.println(Objects.requireNonNull(response));


            // patchForObject()

            // create an empty User
            User user = new User();

            // use valid user id
            user.setId(457);

            // set fields to be changed
            user.setFirstName("Bubba (PATCH)");
            user.setLastName("Rubba (PATCH");

            // send the patch request using the URL, the User created above and the ResponseObject Class
            ResponseObject2 responseUser = restTemplate
                    .patchForObject("http://demo.codingnomads.co:8080/tasks_api/users/" + user.getId(), user,
                            ResponseObject2.class);

            System.out.println(Objects.requireNonNull(responseUser));


            //exchange()

            // create an empty User
            User user2 = new User();

            // set a valid user ID
            user2.setId(455);

            // set fields to be changed
            user2.setFirstName("Fanny");
            user2.setLastName("Pack");


            HttpEntity<User> httpEntity2 = new HttpEntity<>(user2);
            ResponseEntity<ResponseObject2> response2 = restTemplate
                    .exchange("http://demo.codingnomads.co:8080/tasks_api/users/" + user2.getId(), HttpMethod.PATCH,
                            httpEntity2, ResponseObject2.class);

            System.out.println(Objects.requireNonNull(response2));

        };
    }
}