package com.course.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private final String resourceName;
	private final int year;
	private final int semester;

	public ResourceNotFoundException(String resourceName, int year, int semester) {
		super(String.format("%s not found with year:'%s' and semester'%s'", resourceName, year, semester));
		this.resourceName = resourceName;
		this.year = year;
		this.semester = semester;

	}
}


