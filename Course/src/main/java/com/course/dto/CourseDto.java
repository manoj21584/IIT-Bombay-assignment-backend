package com.course.dto;

import com.course.entities.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CourseDto  {
    private Long id;
    @NotBlank(message = "title name cannot be blank ")
//    @Pattern(regexp = "^[\\S]+$", message = "title  must not contain any whitespace")
    private String title;
    @NotBlank(message = "courseCode name cannot be blank ")
//    @Pattern(regexp = "^[\\S]+$", message = "courseCode  must not contain any whitespace")
    private String courseCode;
    @NotBlank(message = "description name cannot be blank ")
//    @Pattern(regexp = "^\\S+$", message = "Description must not contain any whitespace")
    private String description;
}
