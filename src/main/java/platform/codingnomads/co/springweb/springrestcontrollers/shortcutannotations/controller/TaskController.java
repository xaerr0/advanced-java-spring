package platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.model.Task;
import platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.repostiory.TaskRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createNewTask(@RequestBody Task task) throws URISyntaxException {

        //TODO Not quite sure what to do. Post in Postman but debug in here??
        /**
         * Time to POST some tasks! This is very much like the practice you did earlier with the actual Demo API,
         * but this time you get to see what is taking place server-side. Inside TaskController, set a breakpoint in
         * the createNewTask() method, and POST a new task using Postman. Carefully step through the debugger to watch
         * what is taking place. POST at least 4 new tasks.
         * https://platform.codingnomads.co/learn/mod/page/view.php?id=5045&forceview=1
         */


        if (StringUtils.isEmpty(task.getName()) || task.getId() != null) {
            throw new IllegalStateException();
        }
        final Task savedTask = taskRepository.save(task);

        return ResponseEntity.created(new URI("/api/tasks/" + savedTask.getId()))
                .body(savedTask);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable Long id) {

        Optional<Task> taskToReturn = taskRepository.findById(id);

        if (taskToReturn.isPresent()) {
            return ResponseEntity.ok().body(taskToReturn.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        if (id == null || !taskRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok().body(id);
    }

    //TODO This right???
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@RequestBody Task task) throws URISyntaxException {

        if (StringUtils.isEmpty(task.getName()) || task.getId() != null) {
            throw new IllegalStateException();
        }
        final Task updateTask = taskRepository.save(task);

        return ResponseEntity.created(new URI("/tasks_api/tasks/" + updateTask.getId())).body(updateTask);
    }


}