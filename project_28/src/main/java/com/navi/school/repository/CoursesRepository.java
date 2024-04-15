package com.navi.school.repository;

import com.navi.school.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    List<Courses> findAllByOrderByName();

    List<Courses> findAllByOrderByNameDesc();

}
