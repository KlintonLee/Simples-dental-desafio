package com.simples.dental.professionals.domain.exceptions;

public class UnprocessableEntityException extends NoStackTraceException {
    public UnprocessableEntityException(String message) {
        super(message);
    }
}
