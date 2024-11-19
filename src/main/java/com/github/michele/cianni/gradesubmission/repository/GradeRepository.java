package com.github.michele.cianni.gradesubmission.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.github.michele.cianni.gradesubmission.entity.Grade;

import jakarta.transaction.Transactional;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    Iterable<Grade> findByStudentId(Long studentId);

    Iterable<Grade> findByCourseId(Long courseId);
    
    @Transactional void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
}
