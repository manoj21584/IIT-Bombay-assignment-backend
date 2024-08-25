package com.course.controllers;

import com.course.dto.CourseDto;
import com.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@Valid  @RequestBody CourseDto courseDto) {
        CourseDto course = courseService.createCourse(courseDto);
        return ResponseEntity.ok(course);
    }
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourse(){
        List<CourseDto> allCourse = courseService.getAllCourse();
        return ResponseEntity.ok(allCourse);

    }
    @GetMapping("{id}")
    public ResponseEntity<CourseDto> getSingleCourse(@PathVariable Long id){
        CourseDto course = courseService.getSingleCourse(id);
        return ResponseEntity.ok(course);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletedCourse(@PathVariable Long id){
        String course = courseService.deleteCourseById(id);
        return ResponseEntity.ok(course);

    }

}
