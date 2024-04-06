package com.navi.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

@Entity
@Data
@Validated
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int personId;

    @NotBlank(message = "Name can not be blank")
    @Min(value = 3, message = "Name must have minimum 3 characters")
    private String name;

    @NotBlank(message = "Mobile number can not be blank")
    @Pattern(regexp = "^$|[0-9]{10}")
    private String mobileNumber;

    @NotBlank(message = "Email number must not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "Email number must not be blank")
    @Email(message = "Please provide valid email address")
    private String confirmEmail;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 5, message = "Password must be 5 character log")

    private String pwd;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 5, message = "Password must be 5 character log")
    private String confirmPwd;

}
