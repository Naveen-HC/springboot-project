package com.navi.school.validator;

import com.navi.school.anotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    String fieldName1;
    String fieldName2;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.fieldName1 = constraintAnnotation.fieldNane1();
        this.fieldName2 = constraintAnnotation.fieldName2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue1 = new BeanWrapperImpl(value).getPropertyValue(this.fieldName1);
        Object fieldValue2 = new BeanWrapperImpl(value).getPropertyValue(this.fieldName2);
        if(fieldValue2 != null){
            return fieldValue2.equals(fieldValue1);
        } else {
            return false;
        }
    }
}
