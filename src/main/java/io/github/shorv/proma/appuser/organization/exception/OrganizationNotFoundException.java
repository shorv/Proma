package io.github.shorv.proma.appuser.organization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Organization not found")
public class OrganizationNotFoundException extends RuntimeException {
}
