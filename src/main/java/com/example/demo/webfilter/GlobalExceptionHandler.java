package com.example.demo.webfilter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Component
@ControllerAdvice
public class GlobalExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFoundError(NoHandlerFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
