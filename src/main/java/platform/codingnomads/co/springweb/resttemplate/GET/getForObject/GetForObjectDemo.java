package platform.codingnomads.co.springweb.resttemplate.GET.getForObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import platform.codingnomads.co.springweb.resttemplate.GET.models.TastyResponse;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class GetForObjectDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GetForObjectDemo.class, args);
    }

    @Bean

    public static RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean

    public CommandLineRunner run() throws Exception {
        return args -> {

//            QuoteTemplate[] randomQuote;
//            randomQuote = restTemplate.getForObject("https://zenquotes.io/api/random/", QuoteTemplate[].class);
//            System.out.println(Arrays.toString(randomQuote));

            // submit more requests here

//            CodingNomadsTasksApiResponse response =
//                    restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/5",
//                            CodingNomadsTasksApiResponse.class);
////
//            System.out.println(response.toString());

//            RandomFactsTemplate randomFacts =
//                    restTemplate.getForObject("https://uselessfacts.jsph.pl/random.json?language=en",
//                        RandomFactsTemplate.class);
//
//            System.out.println(randomFacts.toString());


            HttpHeaders headers = new HttpHeaders();
            headers.add("X-RapidAPI-Key", "a051247f27msh4a9f4086c6e6991p1235adjsnde5f31eb9f20");
            headers.add("X-RapidAPI-Host", "tasty.p.rapidapi.com");

            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
            Map<String, String> params = new HashMap<>();
            params.put("prefix", "chicken");

            URI uri = UriComponentsBuilder.fromUriString(
                            "https://tasty.p.rapidapi.com/recipes/auto-complete")
                    .queryParam("prefix", "chicken soup")
                    .build().toUri();

            ResponseEntity<TastyResponse> response = restTemplate.exchange(
                    uri, HttpMethod.GET, httpEntity, TastyResponse.class);

            System.out.println(response.getBody());


        };
    }
}