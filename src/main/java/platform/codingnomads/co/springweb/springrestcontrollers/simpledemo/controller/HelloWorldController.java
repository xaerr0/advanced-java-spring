package platform.codingnomads.co.springweb.springrestcontrollers.simpledemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.springweb.resttemplate.PATCH.models.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class HelloWorldController {

    User user;

    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello Spring Developer!";
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@PathVariable(name = "name") String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping(path = "/greetinglist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greetingList() {
        ArrayList<String> greetingList = new ArrayList<>();
        greetingList.add("Hello!");
        greetingList.add("Hola!");
        greetingList.add("Yo!");
        greetingList.add("Ciao!");

        return greetingList.toString();
    }

    @RequestMapping(path = "/userpojo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> users() {

        User user1 = new User();
        user1.setEmail("bob@bobber.com");
        user1.setFirstName("Bob");
        user1.setLastName("Bobber");

        User user2 = new User();
        user2.setEmail("tim@timmy.com");
        user2.setFirstName("Tim");
        user2.setLastName("Timmy");

        return new ArrayList<>(List.of(user1, user2));
    }


}