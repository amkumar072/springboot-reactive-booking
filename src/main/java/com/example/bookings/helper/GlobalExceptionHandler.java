package com.example.bookings.helper;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;


@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Object> webExchangeBindException(WebExchangeBindException e) {
        log.error("GlobalExceptionHandler :: webExchangeBindException()");
        e.printStackTrace();
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage);
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeExceptionHandler(RuntimeException e) {
        log.error("GlobalExceptionHandler :: runtimeExceptionHandler()");
        e.printStackTrace();
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericExceptionHandler(Exception e) {
        log.error("GlobalExceptionHandler :: genericExceptionHandler()");
        e.printStackTrace();

        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
