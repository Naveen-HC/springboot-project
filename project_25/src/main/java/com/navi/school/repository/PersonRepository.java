package com.navi.school.repository;

import com.navi.school.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByEmail(String email);
}
