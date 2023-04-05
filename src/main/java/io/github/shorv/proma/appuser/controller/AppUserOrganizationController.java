package io.github.shorv.proma.appuser.controller;

import io.github.shorv.proma.appuser.AppUser;
import io.github.shorv.proma.appuser.AppUserDTO;
import io.github.shorv.proma.appuser.AppUserService;
import io.github.shorv.proma.organization.OrganizationDTO;
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
public class AppUserOrganizationController {

    private final AppUserService appUserService;

    @PostMapping()
    public ResponseEntity<OrganizationDTO> createUserOrganization(@PathVariable Long userId, @RequestBody OrganizationDTO organizationDTO) {
        appUserService.createOrganization(userId, organizationDTO);
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
        List<OrganizationDTO> organizations = appUserService.getOrganizations(userId);
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationDTO> getUserOrganization(@PathVariable Long userId, @PathVariable Long organizationId) {
        OrganizationDTO organization = appUserService.getOrganization(userId, organizationId);
        return ResponseEntity.ok(organization);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<AppUserDTO> updateUserOrganization(@PathVariable Long userId, @PathVariable Long organizationId, @RequestBody OrganizationDTO organizationDTO) {
        AppUser user = appUserService.getUser(userId);
        AppUserDTO updatedUser = appUserService.updateOrganization(user, organizationId, organizationDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{organizationId}")
    public void deleteUserOrganization(@PathVariable Long userId, @PathVariable Long organizationId){
        appUserService.deleteUserOrganization(userId, organizationId);
    }
}
