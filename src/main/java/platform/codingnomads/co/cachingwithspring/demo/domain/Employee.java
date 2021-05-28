package platform.codingnomads.co.cachingwithspring.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;

}

