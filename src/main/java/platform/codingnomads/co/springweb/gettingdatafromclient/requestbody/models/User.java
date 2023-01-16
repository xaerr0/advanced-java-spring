package platform.codingnomads.co.springweb.gettingdatafromclient.requestbody.models;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;
}