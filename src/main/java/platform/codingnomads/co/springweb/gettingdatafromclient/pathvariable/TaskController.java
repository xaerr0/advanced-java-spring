package platform.codingnomads.co.springweb.gettingdatafromclient.pathvariable;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    @GetMapping(value = "/{id}/{name}/{completed}")
    public Task getTask(@PathVariable(name = "id") Long id, @PathVariable(name = "name") String name, @PathVariable(name = "completed") Boolean completed) {
        return Task.builder().id(id).name(name).completed(completed).build();
    }

    @GetMapping(value = {"/path-variable-optional", "/path-variable-optional/{name}"})
    public String pathVariableOptional(@PathVariable(required = false) String name) {
        if (!StringUtils.isEmpty(name)) {
            return "Path Variable value:" + name;
        } else {
            return "Path Variable Value : Not Provided";
        }
    }

    @GetMapping(value = "/with-map/{id}/{name}/{completed}")
    public Task getTask(@PathVariable Map<String, String> pathVariableMaps) {
        return Task.builder()
                .id(Long.valueOf(pathVariableMaps.get("id")))
                .name(pathVariableMaps.get("name"))
                .completed(Boolean.parseBoolean(pathVariableMaps.get("completed")))
                .build();
    }
}

@Builder
@Data
class Task {
    private Long id;
    private String name;
    private Boolean completed;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}