package io.github.shorv.proma.appuser;

import io.github.shorv.proma.organization.employee.Employee;
import io.github.shorv.proma.organization.Organization;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany
    @JoinTable(name = "organization_employee_mapping",
            joinColumns = {@JoinColumn(name = "appuser_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "organization_id")
    private Map<Organization, Employee> employeeByOrganization;
}
