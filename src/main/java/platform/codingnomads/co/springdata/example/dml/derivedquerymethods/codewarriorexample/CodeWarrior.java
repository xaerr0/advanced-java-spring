package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.codewarriorexample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;



@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Data
public class CodeWarrior {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "email_address_id")
    private EmailAddress emailAddress;

    @Column
    private Integer age;

    @Column
    private Boolean active;
}
