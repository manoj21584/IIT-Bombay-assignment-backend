package com.course.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private int semester;
    @ManyToOne
//    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;
}
