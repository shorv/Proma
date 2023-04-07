package io.github.shorv.proma.appuser.organization.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.shorv.proma.appuser.organization.Organization;
import io.github.shorv.proma.appuser.organization.employee.Employee;
import io.github.shorv.proma.appuser.organization.task.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore()
    private Organization organization;

    @OneToOne
    private Employee leader;

    @OneToMany(mappedBy = "team")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "team")
    private Set<Task> tasks;

    private String name;
}
