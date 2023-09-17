package com.depo.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CodebaseNotFoundException extends NotFoundException {
    public CodebaseNotFoundException() {
    }
}
