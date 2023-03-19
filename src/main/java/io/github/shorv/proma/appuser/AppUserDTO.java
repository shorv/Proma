package io.github.shorv.proma.appuser;

import io.github.shorv.proma.organization.Organization;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AppUserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Organization> organizations;
}
