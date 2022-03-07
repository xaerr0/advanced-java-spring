package platform.codingnomads.co.springweb.returningdatatoclient.usingresponseentity.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import platform.codingnomads.co.springweb.returningdatatoclient.usingresponseentity.model.User;

import java.net.URI;

@RestController
public class ResponseController {

    User user = new User(1, "Test User", "test@email.com");

    @GetMapping("/constructor")
    public ResponseEntity<User> constructorMethod() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("TEST", "TEST HEADER");
        headers.add("Location", "/users/" + user.getId());
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @GetMapping("/builder")
    public ResponseEntity<User> builderMethod() {
        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .header("TEST", "TEST HEADER")
                .body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        if (user.getId() == id ) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
