package com.github.michele.cianni.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.michele.cianni.gradesubmission.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    
}
