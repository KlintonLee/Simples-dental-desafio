package com.simples.dental.professionals.exceptions;

public class UnprocessableEntityException extends NoStackTraceException {
    public UnprocessableEntityException(String message) {
        super(message);
    }
}
