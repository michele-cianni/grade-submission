package com.github.michele.cianni.gradesubmission.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.michele.cianni.gradesubmission.entity.Course;
import com.github.michele.cianni.gradesubmission.service.CourseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> save(@Valid @RequestBody Course course) {        
        return ResponseEntity.ok(courseService.save(course));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(Long id) {
        return ResponseEntity.ok(courseService.getCourse(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Course>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    

}
