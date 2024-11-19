package com.github.michele.cianni.gradesubmission.exception;

public class StudentNotEnrolledException extends RuntimeException {

    public StudentNotEnrolledException(Long studentId, Long courseId) {
        super(String.format("Student with id %d is not enrolled in course with id %d", studentId, courseId));
    }

}
