package com.github.michele.cianni.gradesubmission.service;

import com.github.michele.cianni.gradesubmission.entity.Grade;

public interface GradeService {
    Grade save(Grade grade, Long studentId, Long courseId);
    Grade getGrade(Long studentId, Long courseId);
    Iterable<Grade> getGrades();
    Iterable<Grade> getStudentGrades(Long studentId);
    Iterable<Grade> getCourseGrades(Long courseId);
    Grade updateGrade(String score, Long studentId, Long courseId);
    Grade deleteGrade(Long studentId, Long courseId);
}
