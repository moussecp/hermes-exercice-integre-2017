package com.hermes_ecs.java_exercise.controller;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomeControllerTest {
    @Test
    public void showHomePage() {
        // given
        final HomeController controller = new HomeController();

        // when
        final String page = controller.showHomePage();

        // then
        assertThat(page, is(equalTo("index")));
    }
}
