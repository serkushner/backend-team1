package com.exadel.project.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(Exception e) {
        String exMessage = exceptionLoadMessage(e, "Entity not found exception");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExistsException(Exception e) {
        String exMessage = exceptionLoadMessage(e, "Entity already exists exception");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    private String exceptionLoadMessage(Exception e, String defaultMessage) {
        String exMessage;
        if (e.getMessage() != null) {
            exMessage = e.getMessage().isEmpty()? defaultMessage : e.getMessage();
        } else {
            exMessage = defaultMessage;
        }
        return exMessage;
    }
}
