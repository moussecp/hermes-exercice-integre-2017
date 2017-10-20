package com.hermes_ecs.java_exercise.controller.validation;

import com.hermes_ecs.java_exercise.controller.dto.ProductForm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductFormValidatorTest {
    @Test
    public void supportsProductForm() {
        // given
        ProductFormValidator validator = new ProductFormValidator();

        // when
        final boolean supported = validator.supports(ProductForm.class);

        // then
        assertThat(supported, is(true));
    }
}
