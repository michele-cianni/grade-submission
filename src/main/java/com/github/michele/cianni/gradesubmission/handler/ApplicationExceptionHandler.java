package com.github.michele.cianni.gradesubmission.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.michele.cianni.gradesubmission.exception.CourseNotFoundException;
import com.github.michele.cianni.gradesubmission.exception.GradeNotFoundException;
import com.github.michele.cianni.gradesubmission.exception.StudentNotEnrolledException;
import com.github.michele.cianni.gradesubmission.exception.StudentNotFoundException;

import jakarta.annotation.Nullable;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ 
        CourseNotFoundException.class, 
        GradeNotFoundException.class, 
        StudentNotFoundException.class,
        StudentNotEnrolledException.class 
    })
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.with(ex.getMessage()));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.with("Cannot delete a non-existing resource"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.with("Data Integrity Violation: we cannot process your request."));
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, 
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.with(ex.getBindingResult().getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toArray(String[]::new)));
    }

    
}
