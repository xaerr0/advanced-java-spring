package platform.codingnomads.co.springweb.gettingdatafromclient.requestparam.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Task {
    private Long id;
    private String name;
    @Builder.Default
    private Boolean completed = false;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}