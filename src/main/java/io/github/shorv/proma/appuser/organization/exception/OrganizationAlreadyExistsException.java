package io.github.shorv.proma.appuser.organization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Organization with given name already exists")
public class OrganizationAlreadyExistsException extends RuntimeException {
}
