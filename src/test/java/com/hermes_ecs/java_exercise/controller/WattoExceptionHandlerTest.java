package com.hermes_ecs.java_exercise.controller;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WattoExceptionHandlerTest {
    @Test
    public void handlerAddsExceptionToModel() {
        // given
        final Exception exception = new IllegalArgumentException();

        // when
        final WattoExceptionHandler handler = new WattoExceptionHandler(exception);


        // then
        assertThat(handler.getModel().get(WattoExceptionHandler.EXCEPTION_ATTRIBUTE), is(equalTo((Object) exception)));
    }
}
