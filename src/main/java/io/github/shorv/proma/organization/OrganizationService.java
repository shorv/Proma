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

    private OrganizationDTO convertToDto(Organization organization) {
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    private Organization convertToEntity(OrganizationDTO organizationDTO) throws ParseException {
        return modelMapper.map(organizationDTO, Organization.class);
    }

    public void createOrganization(OrganizationDTO organizationDTO) {
        organizationRepository.save(convertToEntity(organizationDTO));
    }


    public List<OrganizationDTO> getOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public OrganizationDTO getOrganizationById(Long id) {
        return convertToDto(organizationRepository.findById(id)
                .orElseThrow(OrganizationNotFoundException::new));
    }

    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(OrganizationNotFoundException::new);

        organization.setName(organizationDTO.getName());
        organization.setEmployees(organizationDTO.getEmployees());
        organization.setOwner(organizationDTO.getOwner());
        organization.setTasks(organizationDTO.getTasks());
        organization.setTeams(organizationDTO.getTeams());

        organizationRepository.save(organization);
        return convertToDto(organization);
    }

    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }
}
