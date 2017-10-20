package com.hermes_ecs.java_exercise.controller.validation;

import com.hermes_ecs.java_exercise.controller.dto.BuyerForm;
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
public class BuyerFormValidatorParameterizedTest {
    @Parameterized.Parameter(0)
    public String firstName;
    @Parameterized.Parameter(1)
    public String lastName;
    @Parameterized.Parameter(2)
    public int expctedErrorCount;

    @Parameterized.Parameters(name = "{1}, {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Luke", "Skywalker", 0},
                {null, null, 2},
                {"", "", 2},
                {"Luke", "", 1}
        });
    }

    @Test
    public void validate() {
        // given
        BuyerForm form = new BuyerForm();
        form.setFirstName(firstName);
        form.setLastName(lastName);
        BuyerFormValidator validator = new BuyerFormValidator();
        Errors errors = new BeanPropertyBindingResult(form, "form");

        // when
        validator.validate(form, errors);

        // then
        assertThat(errors.getErrorCount(), is(equalTo(expctedErrorCount)));
    }
}
