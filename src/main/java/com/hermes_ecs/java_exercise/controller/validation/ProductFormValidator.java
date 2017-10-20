package com.hermes_ecs.java_exercise.controller.validation;

import com.hermes_ecs.java_exercise.controller.dto.ProductForm;
import com.hermes_ecs.java_exercise.domain.RepublicDactaryAmount;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProductFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "label", "label.required");
        ValidationUtils.rejectIfEmpty(errors, "price", "price.required");

        if (!errors.hasErrors()) {
            final String price = (String) errors.getFieldValue("price");
            if (!RepublicDactaryAmount.isValid(price)) {
                errors.rejectValue("price", "price.invalid");
            }
        }
    }


}
