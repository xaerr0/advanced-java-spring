package platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.model.Task;
import platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.repostiory.TaskRepository;

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private final TaskRepository taskRepository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createNewTask(@RequestBody Task task) throws URISyntaxException {
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

        if(taskToReturn.isPresent()) {
            return ResponseEntity.ok().body(taskToReturn.get());
        }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateCustomer(@RequestBody Task task) {
        if (task.getId() == null) {
            throw new IllegalStateException("Invalid ID");
        }
        Task result = taskRepository.save(task);
        return ResponseEntity.ok()
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalStateException();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok().body(id);
    }
}
