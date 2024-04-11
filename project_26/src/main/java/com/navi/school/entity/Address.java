package com.navi.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int addressId;

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
