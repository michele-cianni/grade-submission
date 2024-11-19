package com.github.michele.cianni.gradesubmission.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.github.michele.cianni.gradesubmission.entity.Course;
import com.github.michele.cianni.gradesubmission.entity.Student;
import com.github.michele.cianni.gradesubmission.exception.StudentNotFoundException;
import com.github.michele.cianni.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        return this.getStudentById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    @Override
    public Set<Course> getEnrolledCourses(Long id) {
        return this.getStudent(id).getCourses();
    }
    
    private Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}
