package com.warmup.project.advice;

import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionResponse> illegalException(Exception exception, WebRequest request){
        System.out.println("An error occured on this request:");
        System.out.println(request);
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),400,"Bad Request", exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    //Method for integrity constraint.
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<IntegrityViolationResponse> integrityException(ConstraintViolationException exception, WebRequest request){
        System.out.println("An error occured on this request:");
        System.out.println(request);
        Map<String, String> fields = new HashMap<>();
        exception.getConstraintViolations().forEach(err->fields.put(err.getPropertyPath().toString(),err.getMessageTemplate()));
        IntegrityViolationResponse exceptionResponse = new IntegrityViolationResponse(LocalDateTime.now(),400,"Bad Request", "Integrity Violation", fields);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



//    @Override
//        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, String> fields = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            fields.put(fieldName, errorMessage);
//        });
//        IntegrityViolationResponse exceptionResponse = new IntegrityViolationResponse(LocalDateTime.now(),400,"Bad Request", "Integrity Violation", fields);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

}
