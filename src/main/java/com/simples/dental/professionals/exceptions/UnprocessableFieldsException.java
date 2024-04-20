package com.simples.dental.professionals.exceptions;

public class UnprocessableFieldsException extends NoStackTraceException {
    public UnprocessableFieldsException(String message) {
        super(message);
    }
}
