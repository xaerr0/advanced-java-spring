package platform.codingnomads.co.springsecurity.authorization.custompermissions.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class MyGrantedAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //object type that authority is granted on
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

    public MyGrantedAuthority(String objectType, Long objectId, String permission) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.permission = permission;
    }

    //put together a grantedAuthorityString after the object is loaded from the database
    @PostLoad
    private void compileGrantedAuthorityString() {
        grantedAuthorityString = objectId + "_" + objectType + "_" + permission;
    }

    public String getAuthority() {
        return grantedAuthorityString;
    }

    @PrePersist
    @PreUpdate
    private void validateData() {
        permission = permission.trim().toLowerCase();
    }
}
