package io.github.shorv.proma.appuser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
