package ru.pdsonline.wrtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.pdsonline.wrtest.exception.NotFoundException;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException e, WebRequest request){
        log.info("{}, request: {}", e.getMessage(), request);
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage())
                .title("Resource not found").property("timestamp", Instant.now()).build();
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request){
        String detailedError = e.getMostSpecificCause() != null ? e.getMostSpecificCause().getMessage() : e.getMessage();
        log.error("{}, Request: {}", e.getLocalizedMessage(), request);
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, detailedError)
                .title("Check request parameters")
                .property("timestamp", Instant.now())
                .build();
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest request){
        String detailedError = e.getMostSpecificCause() != null ? e.getMostSpecificCause().getMessage() : e.getMessage();
        log.error("Failed to read HTTP message. Request: {}. Error details:{}", request, detailedError);
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, detailedError)
                .title("Check request parameters or format")
                .property("timestamp", Instant.now())
                .build();
    }
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e, WebRequest request){
        log.error("Unexpected error: {}. Request: {}", e.getMessage(), request);
        return ErrorResponse.builder(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())
                .title("An unexpected error occurred")
                .property("timestamp", Instant.now())
                .build();
    }
}
