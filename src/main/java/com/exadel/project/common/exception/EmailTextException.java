package com.exadel.project.common.exception;

public class EmailTextException extends RuntimeException {
    public EmailTextException(Exception exception) {
        super(exception);
    }

    public EmailTextException() {}
}
