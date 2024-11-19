package com.github.michele.cianni.gradesubmission.exception;

public class GradeNotFoundException extends RuntimeException {

    public GradeNotFoundException(Long studentId, Long courseId) {
        super(String.format("Grade not found for student %d and course %d", studentId, courseId));
    }

}
