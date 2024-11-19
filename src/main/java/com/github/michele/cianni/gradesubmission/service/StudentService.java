package com.github.michele.cianni.gradesubmission.service;

import java.util.Set;

import com.github.michele.cianni.gradesubmission.entity.Course;
import com.github.michele.cianni.gradesubmission.entity.Student;

public interface StudentService {
    Student save(Student student);
    Student getStudent(Long id);
    Iterable<Student> getStudents();
    void deleteStudent(Long id);
    Set<Course> getEnrolledCourses(Long id);
}
