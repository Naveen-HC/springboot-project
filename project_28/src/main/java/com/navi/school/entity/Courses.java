package com.navi.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@Entity
public class Courses extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int courseId;

    private String name;

    private String fees;

    @ManyToMany(targetEntity = Person.class)
    @JoinTable( name = "person_courses",
            joinColumns =  @JoinColumn(name = "course_id", referencedColumnName = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "personId")
    )
    private List<Person> persons;
}
