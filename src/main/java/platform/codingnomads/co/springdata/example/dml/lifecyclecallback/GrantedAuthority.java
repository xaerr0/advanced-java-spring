package platform.codingnomads.co.springdata.example.dml.lifecyclecallback;

import lombok.*;

import javax.persistence.*;
import java.util.NoSuchElementException;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    //object type authority is granted on
    @Column(nullable = false)
    private String objectType;

    //id of object
    @Column(nullable = false)
    private Long objectId;

    //permission granted (read, update, delete, share, owner)
    @Column(nullable = false)
    private String permission;

    @Transient
    private String grantedAuthorityString;

    //put together a grantedAuthorityString after the object is loaded from the database
    @PostLoad
    private void compileGrantedAuthorityString() {
        grantedAuthorityString = objectType + "_" + objectId + "_" + permission;
    }

    @PrePersist
    @PreUpdate
    private void validateData() {
        permission = permission.trim().toLowerCase();
        confirmObjectExists();
    }

    private void confirmObjectExists() {
        if(/*implement confirmation logic*/false) {
            throw new NoSuchElementException("That object does not exist");
        }
    }
}
