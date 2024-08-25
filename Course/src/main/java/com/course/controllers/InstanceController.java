package com.course.controllers;

import com.course.dto.CourseDto;
import com.course.dto.InstanceDto;
import com.course.service.impl.InstanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instance")
public class InstanceController {
    @Autowired
    private InstanceService instanceService;
    @PostMapping
    public ResponseEntity<InstanceDto> createInstance(@Valid @RequestBody InstanceDto instanceDTO) {
        return ResponseEntity.ok(instanceService.createInstance(instanceDTO));
    }
    @GetMapping("/{year}/{semester}")
    public ResponseEntity<List<CourseDto>> getAllInstance(@PathVariable int year, @PathVariable int semester) {
        return ResponseEntity.ok(instanceService.getAllInstancesByYearAndSemester(year,semester));
    }

    @GetMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<List<InstanceDto>> getInstanceWithAssociatedCourse(@PathVariable int year, @PathVariable int semester,@PathVariable Long courseId) {
        return ResponseEntity.ok(instanceService.getInstanceWithAssociatedCourse(year,semester,courseId));
    }
    @DeleteMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<String> deleteInstanceWithAssociatedCourse(@PathVariable int year, @PathVariable int semester,@PathVariable Long courseId) {
        return ResponseEntity.ok(instanceService.deleteInstanceWithAssociatedCourse(year,semester,courseId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<InstanceDto>getSingleInstance(@PathVariable Long id) {
        return ResponseEntity.ok(instanceService.getSingleInstanceById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteInstanceById(@PathVariable Long id) {
        return ResponseEntity.ok(instanceService.deleteInstanceById(id));
    }



}
