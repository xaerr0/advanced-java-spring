package platform.codingnomads.co.springweb.resttemplate.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.PUT.models.ResponseObject;
import platform.codingnomads.co.springweb.resttemplate.PUT.models.Task;

@SpringBootApplication
public class putMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(putMain.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            int taskId = 5;

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
            taskToUpdate.setName("updated using put()");
            taskToUpdate.setDescription("this task was updated using RestTemplate's put() method");
            taskToUpdate.setCompleted(true);

            //use put to update the resource on the server
            restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/tasks/", taskToUpdate);
            System.out.println("The task with ID " + taskId + " was successfully updated. It should now look something like this:");
            System.out.println(taskToUpdate);
        };
    }
}
