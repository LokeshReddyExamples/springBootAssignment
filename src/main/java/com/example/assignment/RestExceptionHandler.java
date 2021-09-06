package com.example.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeRecordNotFound.class)
    protected ResponseEntity<Object> handleEntityNotFound(EmployeRecordNotFound ex){
                ApiError error = new ApiError();
                error.setMessage(ex.getMessage());
                error.setStatus(HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(error,error.getStatus());
    }


}
