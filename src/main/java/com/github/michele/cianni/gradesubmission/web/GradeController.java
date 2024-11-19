package com.github.michele.cianni.gradesubmission.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.michele.cianni.gradesubmission.entity.Grade;
import com.github.michele.cianni.gradesubmission.service.GradeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(@Valid @RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.save(grade, studentId, courseId));
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.getGrade(studentId, courseId));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Grade>> getGrades() {
        return ResponseEntity.ok(gradeService.getGrades());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Iterable<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getStudentGrades(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Iterable<Grade>> getCourseGrades(@PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.getCourseGrades(courseId));
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@Valid @RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.updateGrade(grade.getScore(), studentId, courseId));
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.deleteGrade(studentId, courseId));
    }

}
