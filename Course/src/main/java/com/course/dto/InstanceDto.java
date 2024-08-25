package com.course.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstanceDto {

    private Long id;

    @NotNull(message = "Year cannot be null")
    @Min(value = 2000, message = "Year must be no earlier than 2000")
    @Max(value = 2100, message = "Year must be no later than 2100")
    private Integer year;  // Changed to Integer to allow null checks

    @Min(value = 1, message = "Semester must be at least 1")
    private int semester;

    @NotNull(message = "Course information cannot be null")
    private CourseDto course;
}
