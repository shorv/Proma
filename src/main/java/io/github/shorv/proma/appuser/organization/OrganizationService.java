package io.github.shorv.proma.appuser.organization;

import io.github.shorv.proma.appuser.AppUser;
import io.github.shorv.proma.appuser.AppUserService;
import io.github.shorv.proma.appuser.organization.exception.OrganizationAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private AppUserService appUserService;
    private final ModelMapper modelMapper;

    private OrganizationDTO convertToDto(Organization organization) {
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    private Organization convertToEntity(OrganizationDTO organizationDTO) throws ParseException {
        return modelMapper.map(organizationDTO, Organization.class);
    }

    public List<OrganizationDTO> getOrganizations(Long userId) {
        AppUser user = appUserService.getUser(userId);
        return user.getOrganizations()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public OrganizationDTO getOrganization(Long userId, Long organizationId) {
        AppUser user = appUserService.getUser(userId);
        Organization organization = appUserService.getUserOrganization(user, organizationId);

        return modelMapper.map(organization, OrganizationDTO.class);
    }

    public void createOrganization(Long userId, OrganizationDTO organizationDTO) {
        AppUser user = appUserService.getUser(userId);
        if (appUserService.hasOrganization(user, organizationDTO.getName())) {
            throw new OrganizationAlreadyExistsException();
        }

        Organization organization = convertToEntity(organizationDTO);
        organization.setOwner(user);
        user.getOrganizations().add(organization);

        organizationRepository.save(organization);
    }

    public OrganizationDTO updateOrganization(Long userId, Long organizationId, OrganizationDTO organizationDTO) {
        AppUser user = appUserService.getUser(userId);
        Organization organization = appUserService.getUserOrganization(user, organizationId);

        organization.setEmployees(organizationDTO.getEmployees());
        organization.setTasks(organizationDTO.getTasks());
        organization.setName(organizationDTO.getName());
        organization.setTeams(organizationDTO.getTeams());
        organization.setOwner(organizationDTO.getOwner());

        organizationRepository.save(organization);
        return convertToDto(organization);
    }

    public void deleteOrganization(Long userId, Long organizationId) {
        AppUser user = appUserService.getUser(userId);
        Organization userOrganization = appUserService.getUserOrganization(user, organizationId);
        user.getOrganizations().remove(userOrganization);

        appUserService.getAppUserRepository().save(user);
    }
}
