package io.github.shorv.proma.organization.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.shorv.proma.appuser.AppUser;
import io.github.shorv.proma.organization.Organization;
import io.github.shorv.proma.organization.Technology;
import io.github.shorv.proma.organization.task.Task;
import io.github.shorv.proma.organization.team.Team;
import jakarta.persistence.ElementCollection;
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
    @ManyToOne
    private AppUser appUser;
    private String email;
    @JsonBackReference
    @ManyToOne
    private Organization organization;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Technology> technologies;
    @JsonBackReference
    @ManyToOne
    private Team team;
    @JsonManagedReference
    @ManyToMany
    private Set<Task> tasks;
}
