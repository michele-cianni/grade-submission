package com.github.michele.cianni.gradesubmission.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
    public Student deleteStudent(Long id) {
        return this.getStudentById(id).stream()
                .peek(student -> studentRepository.delete(student))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
    
    private Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}
