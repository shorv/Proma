package io.github.shorv.proma.organization.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.shorv.proma.organization.Organization;
import io.github.shorv.proma.organization.employee.Employee;
import io.github.shorv.proma.organization.task.Task;
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
    @OneToOne
    private Employee leader;
    @JsonBackReference
    @ManyToOne
    private Organization organization;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private Set<Employee> employees;
    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private Set<Task> tasks;

}
