package platform.codingnomads.co.springweb.gettingdatafromclient.requestbody;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Boolean completed;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
