package io.github.shorv.proma.appuser.organization;

import io.github.shorv.proma.appuser.AppUser;
import io.github.shorv.proma.appuser.organization.employee.Employee;
import io.github.shorv.proma.appuser.organization.task.Task;
import io.github.shorv.proma.appuser.organization.team.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrganizationDTO {
    private Long id;
    private AppUser owner;
    private Set<Team> teams;
    private Set<Employee> employees;
    private Set<Task> tasks;
    private String name;
}
