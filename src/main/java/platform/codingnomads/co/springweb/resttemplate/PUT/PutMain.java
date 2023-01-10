package platform.codingnomads.co.springweb.resttemplate.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.PUT.models.ResponseObject;
import platform.codingnomads.co.springweb.resttemplate.PUT.models.ResponseObject2;
import platform.codingnomads.co.springweb.resttemplate.PUT.models.Task;
import platform.codingnomads.co.springweb.resttemplate.PUT.models.User;

@SpringBootApplication
public class PutMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PutMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            //use a valid task id
            int taskId = 171;

            //request Task 5 from server
            ResponseObject responseObject = restTemplate
                    .getForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);

            //confirm data was retrieved & avoid NullPointerExceptions
            Task taskToUpdate;
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The task with ID " + taskId + " could not be found");
            } else {
                taskToUpdate = responseObject.getData();
            }

            //update the task information
            taskToUpdate.setName("updated using put() - video demo ");
            taskToUpdate.setDescription("this task was updated using RestTemplate's put() method - video demo");
            taskToUpdate.setCompleted(false);

            //use put to update the resource on the server
            restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(), taskToUpdate);
            //get the task to verify update
            responseObject = restTemplate.getForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);
            System.out.println(responseObject.toString());

            taskToUpdate.setName("updated using exchange() PUT - video demo 2");
            taskToUpdate.setDescription("this task was updated using RestTemplate's exchange() method - video demo 2");

            //create an HttpEntity wrapping the task to update
            HttpEntity<Task> httpEntity = new HttpEntity<>(taskToUpdate);
            //use exchange() to PUT the HttpEntity, and map the response to a ResponseEntity
            ResponseEntity<ResponseObject> response = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(),
                    HttpMethod.PUT, httpEntity, ResponseObject.class);
            System.out.println(response.toString());

            // user ID
            int userID = 461;

            // request user 461 from server
            ResponseObject2 responseObject2 = restTemplate
                    .getForObject("http://demo.codingnomads.co:8080/tasks_api/users/" + userID, ResponseObject2.class);

            // confirm data was retrieved & avoid NullPointerExceptions
            User userToUpdate;
            if (responseObject2 == null) {
                throw new Exception("The server did not return anything.");
            } else if (responseObject2.getData() == null) {
                throw new Exception("The user could not be found");
            } else {
                userToUpdate = responseObject2.getData();
            }

            // update the User info
            userToUpdate.setEmail("updated@email.com");
            userToUpdate.setFirstName("updated firstname");
            userToUpdate.setLastName("updated lastname");

            // use put to update to server
            restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/users/" + userToUpdate.getId(), userToUpdate);

            // get user to verify update
            responseObject2 = restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/" + userID,
                    ResponseObject2.class);
            System.out.println(responseObject2.toString());




            // user ID
            int userID2 = 460;

            // request user 460 from server
            ResponseObject2 responseObject3 = restTemplate
                    .getForObject("http://demo.codingnomads.co:8080/tasks_api/users/" + userID2, ResponseObject2.class);

            // confirm data was retrieved & avoid NullPointerExceptions
            User userToUpdate2;
            if (responseObject3 == null) {
                throw new Exception("The server did not return anything.");
            } else if (responseObject3.getData() == null) {
                throw new Exception("The user could not be found");
            } else {
                userToUpdate2 = responseObject3.getData();
            }

            // update the User info for HttpEntity wrapper
            userToUpdate2.setEmail("updated@email.com (HttpEntity)");
            userToUpdate2.setFirstName("updated firstname (HttpEntity)");
            userToUpdate2.setLastName("updated lastname (HttpEntity)");

            // create an HttpEntity wrapping the task to update
            HttpEntity<User> httpEntity1 = new HttpEntity<>(userToUpdate2);

            // user exchange() to PUT the HttpEntity, and map the response to a ResponseEntity
            ResponseEntity<ResponseObject2> response1 = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/users/" + userToUpdate2.getId(),
                    HttpMethod.PUT, httpEntity1, ResponseObject2.class);
            System.out.println(response1.toString());



        };
    }
}