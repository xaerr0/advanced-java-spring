package platform.codingnomads.co.springweb.gettingdatafromclient.requestbody.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.codingnomads.co.springweb.gettingdatafromclient.requestbody.models.Task;
import platform.codingnomads.co.springweb.gettingdatafromclient.requestbody.models.User;
import platform.codingnomads.co.springweb.gettingdatafromclient.requestbody.repositories.TaskRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody(required = true) Task task) throws URISyntaxException {
        if (StringUtils.isEmpty(task.getName()) || task.getCompleted() == null) {
            task.setCreatedAt(null);
            return ResponseEntity.badRequest().body(task);
        }
        final Task savedTask = taskRepository
                .save(Task.builder()
                        .completed(task.getCompleted()).name(task.getName()).build());

        return ResponseEntity.created(new URI("/api/tasks/" + savedTask.getId()))
                .body(savedTask);
    }

    @PostMapping(value = "/print")
    public ResponseEntity<?> printMessage(@RequestBody(required = false) String message) {

        if (message == null) {
            message = "You did not pass in a message.";
        }
        System.out.println(message);

        if (message.equalsIgnoreCase("I'm a teapot")) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(message);
        } else {
            return ResponseEntity.ok().body(message);
        }
    }


    @PostMapping(value = "/printtest")
    public ResponseEntity<?> printMessage2(@RequestBody(required = false) String message) {

        if (message == null) {
            message = "You entered nada!";
        }
        System.out.println("The message you entered is" + message);

        if (message.length() > 5) {
            return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Too many letters! Broverload!! ");
        } else {
            return ResponseEntity.ok().body(message);
        }
    }

    // TODO Maybe help me create another @RequestBody for more practice
    @PostMapping(value = "test/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask2(@RequestBody(required = true) Task task) throws URISyntaxException {
        if (StringUtils.isEmpty(task.getName()) || task.getCompleted() == null) {
            task.setCreatedAt(null);
            return ResponseEntity.badRequest().body(task);
        }
        final Task savedTask = taskRepository.save(Task.builder()
                .completed(task.getCompleted()).name(task.getName()).build());

        return ResponseEntity.created(new URI("test/tasks" + savedTask.getId()))
                .body(savedTask);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody(required = false) User user) throws URISyntaxException {
        if (user != null) {
            user.setId(100L);
        } else {
            user = new User(101L, "bob", "bobby", "bob@bobby.com");
        }
        return ResponseEntity.created(new URI("/users/" + user.getId())).body(user);
    }


}