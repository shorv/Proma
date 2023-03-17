package io.github.shorv.proma.organization.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.shorv.proma.organization.Organization;
import io.github.shorv.proma.organization.TaskPriority;
import io.github.shorv.proma.organization.employee.Employee;
import io.github.shorv.proma.organization.team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @ManyToOne
    private Organization organization;
    @JsonBackReference
    @ManyToOne
    private Team team;
    @JsonBackReference
    @ManyToMany(mappedBy = "tasks")
    private Set<Employee> employees;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
}
