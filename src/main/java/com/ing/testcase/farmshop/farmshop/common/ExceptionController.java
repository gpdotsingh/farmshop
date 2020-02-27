package com.ing.testcase.farmshop.farmshop.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpServerErrorException.class)
    public HttpEntity handleGlobalException(HttpServerErrorException e) {
        return new ResponseEntity(e.getStatusCode());
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<Object> handleOutOfStockException(
            OutOfStockException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time: ", LocalDateTime.now());
        body.put("Out Of Stock Message: ", ex.getLocalizedMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
