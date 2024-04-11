package com.navi.school.entity;

import com.navi.school.anotation.FieldsValueMatch;
import com.navi.school.anotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Setter
@Getter
@Validated
@FieldsValueMatch.List({
        @FieldsValueMatch(
                fieldNane1 = "email",
                fieldName2 = "confirmEmail",
                message = "Email address do not match"
        ),
        @FieldsValueMatch(
                fieldNane1 = "pwd",
                fieldName2 = "confirmPwd",
                message = "Password do not match"
        )
})
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int personId;

    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, message = "Name must have minimum 3 characters")
    private String personName;

    @NotBlank(message = "Mobile number can not be blank")
    @Pattern(regexp = "^$|[0-9]{10}")
    private String mobileNumber;

    @NotBlank(message = "Email number must not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 5, message = "Password must be 5 character log")
    @PasswordValidator
    private String pwd;

    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = SchoolClass.class)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private SchoolClass schoolClass;

    @ManyToMany(mappedBy = "persons", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Courses.class)
//    @JoinTable(name = "person_courses",
//            joinColumns = @JoinColumn(name ="person_id", referencedColumnName = "PersonId"),
//            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId")
//    )
    private List<Courses> courses;

}
