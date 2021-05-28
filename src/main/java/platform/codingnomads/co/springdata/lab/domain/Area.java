package platform.codingnomads.co.springdata.lab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "areas")
@Builder
public class Area implements Serializable {

    private static final long serialVersionUID = -8664726550453215096L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String code;
}
