package com.navi.school.repository;

import com.navi.school.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
//@RepositoryRestResource(exported = false)
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    List<Courses> findAllByOrderByName();

    List<Courses> findAllByOrderByNameDesc();

}
