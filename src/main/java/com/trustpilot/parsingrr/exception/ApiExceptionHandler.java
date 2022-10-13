package com.trustpilot.parsingrr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.valueOf(e.getCode());
        ApiException apiException = new ApiException(e.getCode(), e.getMessage());
        return new ResponseEntity<>(apiException, badRequest);
    }
}
