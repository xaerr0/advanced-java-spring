package platform.codingnomads.co.springweb.resttemplate.GET.getForObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
<<<<<<< HEAD
import platform.codingnomads.co.springweb.resttemplate.GET.getForObject.video_demo.CodingNomadsTasksApiResponse;
import platform.codingnomads.co.springweb.resttemplate.GET.models.KanyeTemplate;
=======
import platform.codingnomads.co.springweb.resttemplate.GET.models.QuoteTemplate;

import java.util.Arrays;
import java.util.List;
>>>>>>> 5780cbda3eb53f9146d3ba20a6471c25ee36c8dc

@SpringBootApplication
public class GetForObjectDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GetForObjectDemo.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            QuoteTemplate[] randomQuote;
            randomQuote = restTemplate.getForObject("https://zenquotes.io/api/random/", QuoteTemplate[].class);
            System.out.println(Arrays.toString(randomQuote));

            // submit more requests here
            
//            CodingNomadsTasksApiResponse response =
//                    restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/5",
//                            CodingNomadsTasksApiResponse.class);
//
//            System.out.println(response.toString());

        };
    }
}
