package platform.codingnomads.co.springweb.springrestcontrollers.helperannotations.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column
    private String name;
    @Column
    private Boolean completed=false;
}
