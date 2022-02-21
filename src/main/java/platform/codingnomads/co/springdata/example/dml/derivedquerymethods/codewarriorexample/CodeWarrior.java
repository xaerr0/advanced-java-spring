package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.codewarriorexample;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CodeWarrior {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "email_address_id")
    private EmailAddress emailAddress;

    private Integer age;
    private Boolean active;
}
