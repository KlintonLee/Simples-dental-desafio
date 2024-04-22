package com.simples.dental.professionals.domain.exceptions;

public class UnprocessableFieldsException extends NoStackTraceException {
    public UnprocessableFieldsException(String message) {
        super(message);
    }
}
