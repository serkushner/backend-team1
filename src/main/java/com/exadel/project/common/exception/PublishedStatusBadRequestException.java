package com.exadel.project.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "internship published status bad request")
public class PublishedStatusBadRequestException extends RuntimeException {

    public PublishedStatusBadRequestException(String message) {
        super(message);
    }

    public PublishedStatusBadRequestException() {

    }
}
