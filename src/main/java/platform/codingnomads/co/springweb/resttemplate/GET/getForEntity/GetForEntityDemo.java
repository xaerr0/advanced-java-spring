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
import platform.codingnomads.co.springweb.resttemplate.GET.models.BoredTemplateAPI;
import platform.codingnomads.co.springweb.resttemplate.GET.models.QuoteTemplate;
import platform.codingnomads.co.springweb.resttemplate.GET.models.RandomFactsTemplate;

import java.util.Arrays;

@SpringBootApplication
public class GetForEntityDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GetForEntityDemo.class, args);
    }

    @Bean
    public static RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
//            ResponseEntity<QuoteTemplate[]> responseEntity =
//                    restTemplate.getForEntity("https://zenquotes.io/api/random", QuoteTemplate[].class);
//
//            if (responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null) {
//                QuoteTemplate[] quoteTemplate = responseEntity.getBody();
//                System.out.println(Arrays.toString(quoteTemplate));
//            } else {
//                System.out.println("Something went wrong! The response was not marked with status code 200");
//            }


            ResponseEntity<BoredTemplateAPI> responseEntity1 =
                    restTemplate.getForEntity("http://www.boredapi.com/api/activity", BoredTemplateAPI.class);

            if (responseEntity1.getStatusCode().equals(HttpStatus.OK) && responseEntity1.getBody() != null) {
                BoredTemplateAPI boredTemplate = responseEntity1.getBody();
                System.out.println(boredTemplate.toString());

            } else {
                System.out.println("Something went wrong! The response was not marked with status code 200");
            }

        };
    }
}