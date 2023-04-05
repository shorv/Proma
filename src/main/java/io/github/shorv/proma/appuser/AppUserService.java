package io.github.shorv.proma.appuser;

import io.github.shorv.proma.appuser.exception.UserNotFoundException;
import io.github.shorv.proma.organization.Organization;
import io.github.shorv.proma.organization.OrganizationDTO;
import io.github.shorv.proma.organization.exception.OrganizationNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public AppUser getUser(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public Organization getUserOrganization(AppUser user, Long organizationId) {
        return user.getOrganizations().stream()
                .filter(o -> o.getId().equals(organizationId))
                .findAny()
                .orElseThrow(OrganizationNotFoundException::new);
    }

    public List<AppUserDTO> getUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void createUser(AppUserDTO appUserDTO) {
        appUserRepository.save(convertToEntity(appUserDTO));
    }

    public AppUserDTO getUserDTO(Long id) {
        return convertToDto(appUserRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public AppUserDTO updateUser(Long id, AppUserDTO appUserDTO) {
        AppUser userToUpdate = getUser(id);
        userToUpdate.setEmail(appUserDTO.getEmail());
        userToUpdate.setUsername(appUserDTO.getUsername());
        userToUpdate.setFirstName(appUserDTO.getFirstName());
        userToUpdate.setLastName(appUserDTO.getLastName());
        userToUpdate.setOrganizations(appUserDTO.getOrganizations());
        userToUpdate.setPassword(appUserDTO.getPassword());

        appUserRepository.save(userToUpdate);
        return convertToDto(userToUpdate);
    }

    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }

    private AppUserDTO convertToDto(AppUser appUser) {
        return modelMapper.map(appUser, AppUserDTO.class);
    }

    private AppUser convertToEntity(AppUserDTO appUserDTO) throws ParseException {
        return modelMapper.map(appUserDTO, AppUser.class);
    }

    public List<OrganizationDTO> getOrganizations(Long userId) {
        AppUser user = getUser(userId);
        return user.getOrganizations()
                .stream()
                .map(organization -> modelMapper.map(organization, OrganizationDTO.class))
                .collect(Collectors.toList());
    }

    public OrganizationDTO getOrganization(Long userId, Long organizationId) {
        AppUser user = getUser(userId);
        Organization organization = getUserOrganization(user, organizationId);

        return modelMapper.map(organization, OrganizationDTO.class);
    }

    public void createOrganization(Long userId, OrganizationDTO organizationDTO) {
        AppUser user = getUser(userId);
        Organization organization = modelMapper.map(organizationDTO, Organization.class);
        organization.setOwner(user);
        user.getOrganizations().add(organization);
        appUserRepository.save(user);
    }

    public AppUserDTO updateOrganization(AppUser user, Long organizationId, OrganizationDTO organizationDTO) {
        Organization organization = getUserOrganization(user, organizationId);
        organization.setEmployees(organizationDTO.getEmployees());
        organization.setTasks(organizationDTO.getTasks());
        organization.setName(organizationDTO.getName());
        organization.setTeams(organizationDTO.getTeams());
        organization.setOwner(organizationDTO.getOwner());

        appUserRepository.save(user);
        return convertToDto(user);
    }

    public void deleteUserOrganization(Long userId, Long organizationId) {
        AppUser user = getUser(userId);
        Organization userOrganization = getUserOrganization(user, organizationId);
        user.getOrganizations().remove(userOrganization);

        appUserRepository.save(user);
    }
}
