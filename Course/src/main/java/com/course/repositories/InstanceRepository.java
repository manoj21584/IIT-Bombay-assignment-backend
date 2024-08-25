package com.course.repositories;

import com.course.dto.InstanceDto;
import com.course.entities.Instance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstanceRepository extends JpaRepository<Instance,Long> {
    public List<Instance>findByYearAndSemester(int year,int semester);
    void deleteByYearAndSemester(int year,int semester);


}
