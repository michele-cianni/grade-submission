package com.github.michele.cianni.gradesubmission.service;

import com.github.michele.cianni.gradesubmission.entity.Student;

public interface StudentService {
    Student save(Student student);
    Student getStudent(Long id);
    Iterable<Student> getStudents();
    Student deleteStudent(Long id);
}
