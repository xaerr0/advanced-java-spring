package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.codewarriorexample.myexample;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String monitor;
    private String mouse;
    private String keyboard;
    private Boolean forGaming;
}