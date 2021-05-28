package platform.codingnomads.co.springweb.usingRestTemplate.POST.getForLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.usingRestTemplate.POST.getForLocation.controllers.TaskControllerFacade;
import platform.codingnomads.co.springweb.usingRestTemplate.POST.models.ResponseObject;
import platform.codingnomads.co.springweb.usingRestTemplate.POST.models.Task;

import java.net.URI;

@SpringBootApplication
public class postForLocationMain {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TaskControllerFacade facade;

    public static void main(String[] args) {
        SpringApplication.run(postForLocationMain.class, args);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
    webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/Users/bensiegler/Library/Mobile Documents/com~apple~CloudDocs/Documents/CodingShit/CodingNomads/java_spring_content/src/main/java/platform/codingnomads/co/springweb/usingRestTemplate/POST/getForLocation");
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForLocation()")
                    .description("get comfortable using the RestTemplate postForLocation() method")
                    .userId(5)
                    .completed(false)
                    .build();

            URI returnedLocation = restTemplate
                    .postForLocation("http://localhost:8080/tasks_api_facade/tasks", newTask, ResponseObject.class);

            System.out.println(returnedLocation.toURL());
        };
    }

}
