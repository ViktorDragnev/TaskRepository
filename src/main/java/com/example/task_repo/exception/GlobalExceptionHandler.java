package com.example.task_repo.exception;

import com.example.task_repo.model.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return getErrorDetailResponseEntity(exception, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return getErrorDetailResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<Object> getErrorDetailResponseEntity(Exception exception, HttpStatus status) {
        ErrorDetail errorDetails = ErrorDetail
                .builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(status).body(errorDetails);
    }
}

