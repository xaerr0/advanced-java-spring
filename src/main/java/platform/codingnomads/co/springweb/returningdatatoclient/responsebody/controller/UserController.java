package platform.codingnomads.co.springweb.returningdatatoclient.responsebody.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.codingnomads.co.springweb.returningdatatoclient.responsebody.model.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    public User user = User.builder()
            .id(1000)
            .name("Spring Dev")
            .email("dev@codingnomads.co")
            .build();

    //using ResponseBody to return a POJO
    @ResponseBody
    @GetMapping("/response-body")
    public User userResponseBody() {
        return user;
    }

    //using ResponseEntity to return POJO
    @GetMapping("/response-entity")
    public ResponseEntity<User> userResponseEntity() {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //returning a POJO without ResponseBody or using a ResponseEntity - error expected
    // adding @ResponseBody
    @ResponseBody
    @GetMapping("/user")

    public User user() {
        return user;
    }
    // Adding ResponseEntity
//    public ResponseEntity<User> pojoResponseEntity() {
//
//        return ResponseEntity.created(URI.create("/users/" + user.getId()))
//                .header("Header 1", "Header 1 Value")
//                .body(user);
//    }

    @ResponseBody
    @GetMapping("/user-list")
    public List<User> userList() {
        User user2 = User.builder()
                .id(1001)
                .name("Fall Dev")
                .email("dev2@codingnomads.co")
                .build();

        User user3 = User.builder()
                .id(1002)
                .name("Summer Dev")
                .email("dev3@codingnomads.co")
                .build();

        User user4 = User.builder()
                .id(1003)
                .name("Summer Dev")
                .email("dev4@codingnomads.co")
                .build();

        List<User> userList = new ArrayList<>(List.of(user2, user3, user4));
        return userList;
    }




}