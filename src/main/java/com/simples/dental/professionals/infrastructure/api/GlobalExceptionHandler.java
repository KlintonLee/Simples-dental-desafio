package com.simples.dental.professionals.infrastructure.api;

import com.simples.dental.professionals.domain.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<?> handleUnprocessableEntityException(final UnprocessableEntityException ex) {
        log.error("GlobalExceptionHandler.java - UnprocessableEntityException - " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ApiError.from(ex));
    }

    @ExceptionHandler(UnprocessableFieldsException.class)
    public ResponseEntity<?> handleUnprocessableFieldsException(final UnprocessableFieldsException ex) {
        log.error("GlobalExceptionHandler.java - UnprocessableFieldsException - " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError.from(ex));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
        log.error("GlobalExceptionHandler.java - NotFoundException - " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(InvalidPaginationValuesException.class)
    public ResponseEntity<?> handleInvalidPaginationValuesException(final InvalidPaginationValuesException ex) {
        log.error("GlobalExceptionHandler.java - InvalidPaginationValuesException - " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError.from(ex));
    }

    record ApiError(String message) {
        static ApiError from(NoStackTraceException ex) {
            return new ApiError(ex.getMessage());
        }
    }
}
