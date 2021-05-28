package platform.codingnomads.co.springweb.usingRestTemplate.PATCH.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    private long id;
    private long userId;
    private String name;
    private String description;
    private long updatedAt;
    private long createdAt;
    private boolean completed;
}