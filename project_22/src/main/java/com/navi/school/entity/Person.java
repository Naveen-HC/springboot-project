package com.navi.school.entity;

import com.navi.school.anotation.FieldsValueMatch;
import com.navi.school.anotation.PasswordValidator;
import com.navi.school.validator.FieldsValueMatchValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

@Entity
@Data
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
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int personId;

    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, message = "Name must have minimum 3 characters")
    private String name;

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

}
