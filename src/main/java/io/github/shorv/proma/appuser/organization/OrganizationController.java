package io.github.shorv.proma.appuser.organization;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/user/{userId}/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping()
    public ResponseEntity<OrganizationDTO> createUserOrganization(@PathVariable Long userId, @RequestBody OrganizationDTO organizationDTO) {
        organizationService.createOrganization(userId, organizationDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{organizationId}")
                .buildAndExpand(organizationDTO.getId())
                .toUri();

        //FIXME Returned organization id is null - after that response it has appropriate value
        return ResponseEntity.created(uri)
                .body(organizationDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getUserOrganizations(@PathVariable Long userId) {
        List<OrganizationDTO> organizations = organizationService.getOrganizations(userId);
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationDTO> getUserOrganization(@PathVariable Long userId, @PathVariable Long organizationId) {
        OrganizationDTO organization = organizationService.getOrganization(userId, organizationId);
        return ResponseEntity.ok(organization);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<OrganizationDTO> updateUserOrganization(@PathVariable Long userId, @PathVariable Long organizationId, @RequestBody OrganizationDTO organizationDTO) {
        OrganizationDTO updatedOrganization = organizationService.updateOrganization(userId, organizationId, organizationDTO);
        return ResponseEntity.ok(updatedOrganization);
    }

    @DeleteMapping("/{organizationId}")
    public void deleteUserOrganization(@PathVariable Long userId, @PathVariable Long organizationId) {
        organizationService.deleteOrganization(userId, organizationId);
    }
}
