package io.github.shorv.proma.appuser.controller;

import io.github.shorv.proma.appuser.AppUserDTO;
import io.github.shorv.proma.appuser.AppUserService;
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
@RequestMapping(path = "/api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping()
    public ResponseEntity<AppUserDTO> createUser(@RequestBody AppUserDTO appUserDTO) {
        appUserService.createUser(appUserDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appUserDTO.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(appUserDTO);
    }

    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getUsers() {
        List<AppUserDTO> users = appUserService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUser(@PathVariable Long id){
        AppUserDTO user = appUserService.getUserDTO(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @RequestBody AppUserDTO appUserDTO){
        AppUserDTO updatedUser = appUserService.updateUser(id, appUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        appUserService.deleteAppUser(id);
    }
}
