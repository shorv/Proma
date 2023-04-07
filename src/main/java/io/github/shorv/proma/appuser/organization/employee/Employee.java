package io.github.shorv.proma.appuser.organization.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.shorv.proma.appuser.AppUser;
import io.github.shorv.proma.appuser.organization.Organization;
import io.github.shorv.proma.appuser.organization.Technology;
import io.github.shorv.proma.appuser.organization.task.Task;
import io.github.shorv.proma.appuser.organization.team.Team;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppUser appUser;

    @ManyToOne
    @JsonIgnore
    private Organization organization;

    @ManyToOne
    @JsonIgnore
    private Team team;

    @ManyToMany
    private Set<Task> tasks;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Technology> technologies;

    private String email;
}
