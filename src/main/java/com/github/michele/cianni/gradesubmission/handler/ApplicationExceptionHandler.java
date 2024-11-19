package com.github.michele.cianni.gradesubmission.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.michele.cianni.gradesubmission.exception.CourseNotFoundException;
import com.github.michele.cianni.gradesubmission.exception.StudentNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CourseNotFoundException.class, StudentNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.with(ex.getMessage()));
    }

}
