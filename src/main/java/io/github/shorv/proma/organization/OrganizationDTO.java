package io.github.shorv.proma.organization;

import io.github.shorv.proma.organization.employee.Employee;
import io.github.shorv.proma.organization.task.Task;
import io.github.shorv.proma.organization.team.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrganizationDTO {
    private Long id;
//    private AppUser owner;
    private Set<Team> teams;
    private Set<Employee> employees;
    private Set<Task> tasks;
    private String name;
}
