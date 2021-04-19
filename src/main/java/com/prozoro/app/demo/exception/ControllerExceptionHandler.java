package com.prozoro.app.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> accessDenied(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>("ACCESS DENIED", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
