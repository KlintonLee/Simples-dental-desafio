package com.simples.dental.professionals.application.exceptions;

public class UnprocessableEntityException extends NoStackTraceException {
    public UnprocessableEntityException(String message) {
        super(message);
    }
}
