package com.simples.dental.professionals.domain.exceptions;

public class NotFoundException extends NoStackTraceException {

    public NotFoundException(String message) {
        super(message);
    }
}
