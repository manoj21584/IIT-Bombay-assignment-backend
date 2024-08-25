package com.course.service.impl;

import com.course.dto.CourseDto;
import com.course.entities.Course;
import com.course.repositories.CourseRepository;
import com.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course=courseDtoToCourseEntity(courseDto);
        Course save = courseRepository.save(course);
        CourseDto courseDto1=courseToCourseDto(save);
        return courseDto1;
    }

    @Override
    public List<CourseDto> getAllCourse() {
        List<Course> all = courseRepository.findAll();
        List<CourseDto> collect = all.stream().map(course -> courseToCourseDto(course)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CourseDto getSingleCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        CourseDto courseDto = courseToCourseDto(course);
        return courseDto;
    }

    @Override
    public String deleteCourseById(Long id) {
        courseRepository.deleteById(id);
        return "deleted successfully";

    }

    private CourseDto courseToCourseDto(Course course) {
        CourseDto courseDto=new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setCourseCode(course.getCourseCode());
        courseDto.setDescription(course.getDescription());
        return courseDto;

    }

    private Course courseDtoToCourseEntity(CourseDto courseDto) {
        Course course=new Course();
        course.setTitle(courseDto.getTitle());
        course.setCourseCode(courseDto.getCourseCode());
        course.setDescription(courseDto.getDescription());
        return course;
    }
}
