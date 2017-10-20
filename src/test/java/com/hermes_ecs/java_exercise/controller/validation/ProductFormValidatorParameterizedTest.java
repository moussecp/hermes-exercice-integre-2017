package com.hermes_ecs.java_exercise.controller.validation;

import com.hermes_ecs.java_exercise.controller.dto.ProductForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ProductFormValidatorParameterizedTest {
    @Parameterized.Parameter(0)
    public String label;
    @Parameterized.Parameter(1)
    public String price;
    @Parameterized.Parameter(2)
    public int expectedErrorCount;

    @Parameterized.Parameters(name = "{0}, {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Landspeeder", "123", 0},
                {null, null, 2},
                {"", "", 2},
                {"Landspeeder", "", 1}
        });
    }

    @Test
    public void validate() {
        // given
        ProductForm form = new ProductForm();
        form.setLabel(label);
        form.setPrice(price);
        ProductFormValidator validator = new ProductFormValidator();
        Errors errors = new BeanPropertyBindingResult(form, "form");

        // when
        validator.validate(form, errors);

        // then
        assertThat(errors.getErrorCount(), is(equalTo(expectedErrorCount)));
    }
}
