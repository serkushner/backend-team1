package com.exadel.project.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

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
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoubleInternshipRegistrationException.class)
    public ResponseEntity<ExceptionResponse> handlerDoubleInternshipRegistrationException(Exception e){
        String exMessage = exceptionLoadMessage(e, "internship with same start date and title already registered");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        logger.debug("attempt to repeat a registration of the registered internship", e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PublishedStatusBadRequestException.class)
    public ResponseEntity<ExceptionResponse> handlerPublishedStatusBadRequestException(Exception e){
        String exMessage = exceptionLoadMessage(e, "attempt to do a wrong request");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<ExceptionResponse> handlerMailException(Exception e){
        String exMessage = exceptionLoadMessage(e, "error during email sending");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TraineeAlreadyConfirmEmailException.class)
    public ResponseEntity<ExceptionResponse> handlerTraineeAlreadyConfirmEmailException(Exception e){
        String exMessage = exceptionLoadMessage(e, "trainee already confirmed email");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TokenIsNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerTokenIsNotValidException(Exception e){
        String exMessage = exceptionLoadMessage(e, "token is not valid");
        String message = String.format("%s %s", LocalDateTime.now(), exMessage);
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TraineeStatusIsNotAvailableForChangesException.class)
    public ResponseEntity<ExceptionResponse> handlerTraineeStatusIsNotAvailableForChangesException(Exception e){
        String exMessage = exceptionLoadMessage(e, "Trainee status is not available for changes. Please check trainee status ");
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
