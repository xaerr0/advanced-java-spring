package platform.codingnomads.co.springweb.resttemplate.POST.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private long id;
    private long userId;
    private String name;
    private String description;
    private long updatedAt;
    private long createdAt;
    private boolean completed;
}