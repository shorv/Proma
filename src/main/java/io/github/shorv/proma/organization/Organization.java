package io.github.shorv.proma.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.shorv.proma.appuser.AppUser;
import io.github.shorv.proma.organization.employee.Employee;
import io.github.shorv.proma.organization.task.Task;
import io.github.shorv.proma.organization.team.Team;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser owner;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Team> teams;
    @JsonManagedReference
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Employee> employees;
    @JsonManagedReference
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Task> tasks;

}
