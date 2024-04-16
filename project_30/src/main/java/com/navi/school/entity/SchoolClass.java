package com.navi.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@Entity
@Table(name = "class")
public class SchoolClass extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int classId;

    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, message = "Name must have minimum 3 characters")
    private String name;

    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private List<Person> persons;
}
