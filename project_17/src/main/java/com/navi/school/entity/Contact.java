package com.navi.school.entity;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class Contact extends BaseEntity{

    private int contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be 3 characteristic long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email number must not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must be 5 characteristic long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 5, message = "Message must be 7 characteristic long")
    private String message;

    private String status;

}
