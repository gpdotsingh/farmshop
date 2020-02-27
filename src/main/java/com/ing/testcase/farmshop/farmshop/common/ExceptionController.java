package com.ing.testcase.farmshop.farmshop.common;

import com.ing.testcase.farmshop.farmshop.entities.Stockexist;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @Autowired
    Stockexist stockexist;


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullException(HttpServerErrorException e) {
        return new ResponseEntity<>("Please correct your input",EXPECTATION_FAILED);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public HttpEntity handleGlobalException(HttpServerErrorException e) {
        return new ResponseEntity(e.getStatusCode());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public HttpEntity handleBadRequest() {
        return new ResponseEntity(BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public HttpEntity handleGlobalException(Throwable t) {
        return new ResponseEntity(SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<Object> handleOutOfStockException(
            OutOfStockException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time: ", LocalDateTime.now());
        body.put("Out Of Stock Message: ", ex.getLocalizedMessage());
        stockexist.setStockExist(false);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
