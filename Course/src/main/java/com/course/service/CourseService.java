package com.course.service;

import com.course.dto.CourseDto;

import java.util.List;

public interface CourseService {
     CourseDto createCourse(CourseDto courseDto);

    List<CourseDto> getAllCourse();

    CourseDto getSingleCourse(Long id);

    String deleteCourseById(Long id);

}
