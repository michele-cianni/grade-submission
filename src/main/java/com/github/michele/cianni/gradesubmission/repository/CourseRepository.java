package com.github.michele.cianni.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.michele.cianni.gradesubmission.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
    
}
