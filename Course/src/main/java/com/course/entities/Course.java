package com.course.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String courseCode;
    private String description;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Instance> instances;
}
