package platform.codingnomads.co.springweb.usingRestTemplate.POST.getForLocation.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.codingnomads.co.springweb.usingRestTemplate.POST.models.Task;

@RestController("/tasks_api_facade")
public class TaskControllerFacade {

    @PostMapping("/tasks")
    public String postNewTask(Task taskToPost) {
        return "http://www.thisIsADomain.com/tasks/" + taskToPost.getName();
    }
}
