package io.github.shorv.proma.appuser;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Long> createUser(@RequestBody AppUser appUser) {
        appUserService.createUser(appUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appUser.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(appUser.getId());
    }

    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getUsers() {
        List<AppUserDTO> users = appUserService.getUsers();
        return ResponseEntity.ok(users);
    }
}
