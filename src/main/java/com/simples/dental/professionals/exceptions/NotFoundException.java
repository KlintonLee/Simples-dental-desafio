package com.simples.dental.professionals.exceptions;

public class NotFoundException extends NoStackTraceException {

    public NotFoundException(String message) {
        super(message);
    }
}
