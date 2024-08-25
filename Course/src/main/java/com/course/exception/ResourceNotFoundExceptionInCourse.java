package com.course.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionInCourse extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Long fieldValue ;

    public ResourceNotFoundExceptionInCourse(String resourceName, String fieldName, Long courseId) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, courseId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = courseId;

    }
}


