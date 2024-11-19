package com.github.michele.cianni.gradesubmission.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.michele.cianni.gradesubmission.entity.Course;
import com.github.michele.cianni.gradesubmission.entity.Grade;
import com.github.michele.cianni.gradesubmission.entity.Student;
import com.github.michele.cianni.gradesubmission.exception.CourseNotFoundException;
import com.github.michele.cianni.gradesubmission.exception.GradeNotFoundException;
import com.github.michele.cianni.gradesubmission.exception.StudentNotEnrolledException;
import com.github.michele.cianni.gradesubmission.exception.StudentNotFoundException;
import com.github.michele.cianni.gradesubmission.repository.CourseRepository;
import com.github.michele.cianni.gradesubmission.repository.GradeRepository;
import com.github.michele.cianni.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Override
    public Grade save(Grade grade, Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        if (student.isNotEnrolled(course)) throw new StudentNotEnrolledException(studentId, courseId);
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        return getGradeByStudentAndCourse(studentId, courseId).orElseThrow(() -> new GradeNotFoundException(studentId, courseId));
    }

    @Override
    public Iterable<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Iterable<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public Iterable<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Grade grade = getGrade(studentId, courseId);
        grade.setScore(score);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade deleteGrade(Long studentId, Long courseId) {
        return this.getGradeByStudentAndCourse(studentId, courseId).stream()
                .peek(grade -> gradeRepository.delete(grade))
                .findFirst()
                .orElseThrow(() -> new GradeNotFoundException(studentId, courseId));
    }

    private Optional<Grade> getGradeByStudentAndCourse(Long studentId, Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    }
}
