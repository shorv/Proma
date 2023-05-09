package io.github.shorv.proma.organization;

import io.github.shorv.proma.organization.exception.OrganizationNotFoundException;
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
    private final ModelMapper modelMapper;

    public Organization getOrganization(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(OrganizationNotFoundException::new);
    }

    private OrganizationDTO convertToDto(Organization organization) {
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    private Organization convertToEntity(OrganizationDTO organizationDTO) throws ParseException {
        return modelMapper.map(organizationDTO, Organization.class);
    }

    public List<OrganizationDTO> getOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public OrganizationDTO getOrganizationById(Long organizationId) {
        Organization organization = getOrganization(organizationId);
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    public void createOrganization(OrganizationDTO organizationDTO) {
        organizationRepository.save(convertToEntity(organizationDTO));
    }

    public OrganizationDTO updateOrganization(Long organizationId, OrganizationDTO organizationDTO) {
        Organization organization = getOrganization(organizationId);

        organization.setEmployees(organizationDTO.getEmployees());
        organization.setTasks(organizationDTO.getTasks());
        organization.setName(organizationDTO.getName());
        organization.setTeams(organizationDTO.getTeams());

        organizationRepository.save(organization);
        return convertToDto(organization);
    }

    public void deleteOrganization(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }
}
