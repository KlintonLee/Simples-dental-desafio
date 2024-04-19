package com.simples.dental.professionals.application.exceptions;

public class NotFoundException extends NoStackTraceException {

    public NotFoundException(String message) {
        super(message);
    }
}
