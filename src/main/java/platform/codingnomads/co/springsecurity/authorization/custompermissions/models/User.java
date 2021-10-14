package platform.codingnomads.co.springsecurity.authorization.custompermissions.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany
    private List<MyGrantedAuthority> grantedAuthorities;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
