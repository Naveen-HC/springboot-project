package com.navi.school.anotation;

import com.navi.school.validator.FieldsValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FieldsValueMatch {

    String message() default "{Weak password}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldNane1();
    String fieldName2();

    @Target(ElementType.TYPE)
    @Retention(RUNTIME)
    public @interface List {
        FieldsValueMatch[] value();
    }
}
