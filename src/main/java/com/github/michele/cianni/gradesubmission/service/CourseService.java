package com.github.michele.cianni.gradesubmission.service;

import java.util.Set;

import com.github.michele.cianni.gradesubmission.entity.Course;
import com.github.michele.cianni.gradesubmission.entity.Student;

public interface CourseService {
    Course save(Course course);
    Course getCourse(Long id);
    Iterable<Course> getCourses();
    void deleteCourse(Long id);
    Course addStudentToCourse(Long studentId, Long courseId);
    Set<Student> getEnrolledStudents(Long id);
}
