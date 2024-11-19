package com.github.michele.cianni.gradesubmission.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.michele.cianni.gradesubmission.entity.Course;
import com.github.michele.cianni.gradesubmission.exception.CourseNotFoundException;
import com.github.michele.cianni.gradesubmission.repository.CourseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourse(Long id) {
        return this.getCourseById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Override
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course deleteCourse(Long id) {
        return this.getCourseById(id).stream()
                .peek(courseRepository::delete)
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    private Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    
}
