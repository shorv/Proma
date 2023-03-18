package io.github.shorv.proma.appuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;
}
