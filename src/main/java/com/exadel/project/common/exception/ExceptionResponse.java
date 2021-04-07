package com.exadel.project.common.exception;

/**
 * The class for a custom exception response body.
 * It can be changed for any other dto.
 */
public class ExceptionResponse {

    private String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
