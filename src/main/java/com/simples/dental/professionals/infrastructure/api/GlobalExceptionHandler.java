package com.simples.dental.professionals.infrastructure.api;

import com.simples.dental.professionals.exceptions.NoStackTraceException;
import com.simples.dental.professionals.exceptions.NotFoundException;
import com.simples.dental.professionals.exceptions.UnprocessableEntityException;
import com.simples.dental.professionals.exceptions.UnprocessableFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = {UnprocessableFieldsException.class, UnprocessableEntityException.class})
    public ResponseEntity<?> handleUnprocessableFieldsException(final UnprocessableFieldsException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ApiError.from(ex));
    }

    record ApiError(String message) {
        static ApiError from(NoStackTraceException ex) {
            return new ApiError(ex.getMessage());
        }
    }
}
