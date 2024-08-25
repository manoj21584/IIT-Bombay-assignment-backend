package com.course.service.impl;

import com.course.dto.CourseDto;
import com.course.dto.InstanceDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InstanceService {
    InstanceDto createInstance(InstanceDto instanceDto);
    List<CourseDto> getAllInstancesByYearAndSemester(int year, int semester);
    InstanceDto getSingleInstanceById(Long id);


    String deleteInstanceById(Long id);

    List<InstanceDto> getInstanceWithAssociatedCourse(int year, int semester, Long courseId);

    String deleteInstanceWithAssociatedCourse(int year, int semester, Long courseId);

}


