package io.github.shorv.proma.appuser.organization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.shorv.proma.appuser.AppUser;
import io.github.shorv.proma.appuser.organization.employee.Employee;
import io.github.shorv.proma.appuser.organization.task.Task;
import io.github.shorv.proma.appuser.organization.team.Team;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    public Organization(String name) {
        this.name = name;
        this.teams = new HashSet<>();
        this.employees = new HashSet<>();
        this.tasks = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private AppUser owner;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Team> teams;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    private String name;
}
