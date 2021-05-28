package platform.codingnomads.co.springweb.gettingdatafromclient.requestbody;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class TaskController {

    @Nonnull private TaskRepository taskRepository;

    @PostMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException {
        if (task.getId() != null || StringUtils.isEmpty(task.getName()) || task.getCompleted() == null) {
            throw new IllegalStateException();
        }
        final Task savedTask = taskRepository.save(Task.builder().completed(task.getCompleted()).name(task.getName()).build());
        return ResponseEntity.created(new URI("/api/tasks/" + savedTask.getId()))
                .body(savedTask);

    }

}
