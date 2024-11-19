package com.github.michele.cianni.gradesubmission.service;

import com.github.michele.cianni.gradesubmission.entity.Course;

public interface CourseService {
    Course save(Course course);
    Course getCourse(Long id);
    Iterable<Course> getCourses();
    Course deleteCourse(Long id);    
}
