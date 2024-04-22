package com.simples.dental.professionals.domain.exceptions;

public class InvalidPaginationValuesException extends NoStackTraceException {

    public InvalidPaginationValuesException(String message) {
        super(message);
    }
}
