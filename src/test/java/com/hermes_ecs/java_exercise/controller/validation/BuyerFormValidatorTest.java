package com.hermes_ecs.java_exercise.controller.validation;

import com.hermes_ecs.java_exercise.controller.dto.BuyerForm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BuyerFormValidatorTest {
    @Test
    public void supportsBuyer() {
        // given
        BuyerFormValidator validator = new BuyerFormValidator();

        // when
        final boolean supported = validator.supports(BuyerForm.class);

        // then
        assertThat(supported, is(true));
    }
}
