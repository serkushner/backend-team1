package com.exadel.project.common.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
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

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionResponse> handlerIOException(Exception e){
        String exMessage = exceptionLoadMessage(e, "can't upload cv");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ExceptionResponse> handlerPropertyReferenceExceptionAndInvalidDataAccessApiUsageException(Exception e){
        String exMessage = exceptionLoadMessage(e, "Bad request");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoubleRegistrationException.class)
    public ResponseEntity<ExceptionResponse> handlerDoubleRegistrationException(Exception e){
        String exMessage = exceptionLoadMessage(e, "trainee already registered on the internship");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
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
