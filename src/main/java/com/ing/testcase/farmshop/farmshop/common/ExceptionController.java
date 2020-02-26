package com.ing.testcase.farmshop.farmshop.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ControllerAdvice
public class ExceptionController {


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
        System.out.println("Unable to process request.");
        t.printStackTrace();
        return new ResponseEntity(SERVICE_UNAVAILABLE);
    }

}
