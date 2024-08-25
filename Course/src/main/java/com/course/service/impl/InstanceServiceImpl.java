package com.course.service.impl;

import com.course.dto.CourseDto;
import com.course.dto.InstanceDto;
import com.course.entities.Course;
import com.course.entities.Instance;
import com.course.exception.ResourceNotFoundException;
import com.course.exception.ResourceNotFoundExceptionInCourse;
import com.course.repositories.CourseRepository;
import com.course.repositories.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstanceServiceImpl implements InstanceService {
    @Autowired
    private InstanceRepository instanceRepository;
    @Autowired
    private CourseRepository courseRepository;
   @Override
    public InstanceDto createInstance(InstanceDto instanceDto) {
       Course course = courseRepository.findById(instanceDto.getCourse().getId())
               .orElseThrow(() -> new IllegalArgumentException("Course not found"));

       Instance courseInstance = Instance.builder()
               .year(instanceDto.getYear())
               .semester(instanceDto.getSemester())
               .course(course)
               .build();

       Instance savedInstance = instanceRepository.save(courseInstance);
       return toDTO(savedInstance);
    }

    @Override
        public List<CourseDto> getAllInstancesByYearAndSemester(int year, int semester) {
        List<Instance> instances = instanceRepository.findByYearAndSemester(year, semester);
        if(instances.size()<1){
            throw new ResourceNotFoundException("Instance",year,semester);
        }else{
            List<CourseDto> course=new ArrayList<>();
            for(Instance i:instances){
                Course course1 = i.getCourse();
                CourseDto courseDto = mapToCourseDto(course1);
                course.add(courseDto);

            }
            return course;
        }

    }
    @Override
    public List<InstanceDto> getInstanceWithAssociatedCourse(int year, int semester, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundExceptionInCourse("Course","courseId",courseId));
        List<Instance> instances = instanceRepository.findByYearAndSemester(year, semester);
        if(instances.size()<1){
            throw new ResourceNotFoundException("Instance",year,semester);
        }
        List<InstanceDto> instanceDtos = instances.stream()
                .filter(instance -> instance.getCourse().getId().equals(courseId)) // Ensure the course ID matches
                .map(instance -> {
                    return InstanceDto.builder()
                            .id(instance.getId())
                            .year(instance.getYear())
                            .semester(instance.getSemester())
                            .course(CourseDto.builder()
                                    .id(course.getId())
                                    .title(course.getTitle())
                                    .courseCode(course.getCourseCode())
                                    .description(course.getDescription())
                                    .build())
                            .build();
                })
                .collect(Collectors.toList());

        return instanceDtos;
    }

    @Override
    public String deleteInstanceWithAssociatedCourse(int year, int semester, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundExceptionInCourse("course","courseId",courseId));
        List<Instance> instances = instanceRepository.findByYearAndSemester(year, semester)
                .stream()
                .filter(instance -> instance.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());

        if (!instances.isEmpty()) {
            instanceRepository.deleteAll(instances);
        }
        courseRepository.deleteById(courseId);

        return "Deleted successfully";
    }



    private CourseDto mapToCourseDto(Course course1) {
       return CourseDto.builder().id(course1.getId())
               .title(course1.getTitle())
               .courseCode(course1.getCourseCode())
               .description(course1.getDescription())
               .build();

    }

    @Override
    public InstanceDto getSingleInstanceById(Long id) {
        Instance instance = instanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        InstanceDto dto = toDTO(instance);
        return dto;
    }

    @Override
    public String deleteInstanceById(Long id) {
       instanceRepository.findById(id).orElseThrow(()->new ResourceNotFoundExceptionInCourse("Instance","instanceId",id));
        instanceRepository.deleteById(id);
        return "deleted successfully";
    }



    private InstanceDto toDTO(Instance instance) {
        return InstanceDto.builder()
                .id(instance.getId())
                .year(instance.getYear())
                .semester(instance.getSemester())
                .course(CourseDto.builder()
                        .id(instance.getCourse().getId())
                        .title(instance.getCourse().getTitle())
                        .courseCode(instance.getCourse().getCourseCode())
                        .description(instance.getCourse().getDescription())
                        .build())
                .build();
    }
}

