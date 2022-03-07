package platform.codingnomads.co.springweb.resttemplate.GET.getForEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.GET.models.KanyeTemplate;

@SpringBootApplication
public class GetForEntityDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GetForEntityDemo.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            ResponseEntity<KanyeTemplate> responseEntity =
                    restTemplate.getForEntity("https://api.kanye.rest", KanyeTemplate.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null) {
                KanyeTemplate kanyeTemplate = responseEntity.getBody();
                System.out.println(kanyeTemplate.getQuote());
            } else {
                System.out.println("Something went wrong! The response was not marked with status code 200");
            }
        };
    }
}
