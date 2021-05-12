package com.exadel.project.common.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class TraineeAlreadyConfirmEmailException extends RuntimeException {

    public TraineeAlreadyConfirmEmailException(String message) {
        super(message);
    }

    public TraineeAlreadyConfirmEmailException() {

    }
}
