package platform.codingnomads.co.springweb.gettingdatafromclient.pathvariable.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Task {
    private Long id;
    private String name;
    private Boolean completed;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
