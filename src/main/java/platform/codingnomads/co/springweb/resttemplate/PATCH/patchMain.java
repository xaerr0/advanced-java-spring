package platform.codingnomads.co.springweb.resttemplate.PATCH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.PATCH.models.ResponseObject;
import platform.codingnomads.co.springweb.resttemplate.PATCH.models.Task;

import java.util.Objects;

@SpringBootApplication
public class patchMain {

    @Autowired
    RestTemplate restTemplate;


    public static void main(String[] args) {
        SpringApplication.run(patchMain.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            //create an empty Task
            Task task = new Task();

            //set fields for identification
            task.setId(6);

            //set fields we want to change. All other fields are null and will not be updated
            task.setName("use patch()");
            task.setDescription("this task was updated using patch()");

            //send the PATCH request using the URL, the Task created above and the ResponseObject Class
            ResponseObject response = restTemplate
                    .patchForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/", task, ResponseObject.class);

            Objects.requireNonNull(response);

            //inform user of status / updated object
            System.out.println(response.toString());
        };
    }
}
