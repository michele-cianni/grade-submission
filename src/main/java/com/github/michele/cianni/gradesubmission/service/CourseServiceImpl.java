package com.github.michele.cianni.gradesubmission.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.github.michele.cianni.gradesubmission.entity.Course;
import com.github.michele.cianni.gradesubmission.entity.Student;
import com.github.michele.cianni.gradesubmission.exception.CourseNotFoundException;
import com.github.michele.cianni.gradesubmission.exception.StudentNotFoundException;
import com.github.michele.cianni.gradesubmission.repository.CourseRepository;
import com.github.michele.cianni.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

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

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = this.getCourse(courseId);
        course.addStudent(student);
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        return this.getCourse(id).getStudents();
    }

}
