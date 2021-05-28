package platform.codingnomads.co.springweb.usingRestTemplate.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.usingRestTemplate.DELETE.models.ResponseObject;
import platform.codingnomads.co.springweb.usingRestTemplate.DELETE.models.Task;


@ComponentScan(basePackages = "")
@SpringBootApplication
public class deleteMain {

    @Autowired
    RestTemplate restTemplate;


    public static void main(String[] args) {
        SpringApplication.run(deleteMain.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            Task newTask = Task.builder()
                    .name("should be deleted")
                    .description("used in a delete RestTemplate example. If you see this something went wrong. Oops")
                    .userId(5)
                    .completed(false)
                    .build();

            //request Task 5 from server
            ResponseObject responseObject = restTemplate
                    .postForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/", newTask , ResponseObject.class);

            //confirm data was returned & avoid NullPointerExceptions
            if(null == responseObject) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            }else if(null == (newTask = responseObject.getData())) {
                throw new Exception("The server encountered this error while creating the task:" + responseObject.getError().getMessage());
            }

            System.out.println("The task was successfully created");

            //delete the newTask using the ID the server returned
            restTemplate.delete("http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId());
            System.out.println("The task was also successfully deleted");
        };
    }
}
