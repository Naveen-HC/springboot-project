package com.navi.school.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile {

    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, message = "Name must have minimum 3 characters")
    private String name;

    @NotBlank(message = "Mobile number can not be blank")
    @Pattern(regexp = "^$|[0-9]{10}")
    private String mobileNumber;

    @NotBlank(message = "Email number must not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "address can not blank")
    @Size(min = 5, message = "Address must be 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "city can not blank")
    @Size(min = 5, message = "City must be 5 characters long")
    private String city;

    @NotBlank(message = "state can not blank")
    @Size(min = 5, message = "State must be 5 characters long")
    private String state;

    @NotBlank(message = "zipCode can not blank")
    @Pattern(regexp = "^$|[0-9]{5}", message = " Zip code must have 5 digits")
    private String zipCode;

}
