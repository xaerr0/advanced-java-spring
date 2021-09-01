package platform.codingnomads.co.springweb.springrestcontrollers.shortcutannotations.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Task {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column
    private String name;
    @Column
    private Boolean completed=false;
}
