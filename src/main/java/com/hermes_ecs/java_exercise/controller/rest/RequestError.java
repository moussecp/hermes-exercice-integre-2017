package com.hermes_ecs.java_exercise.controller.rest;

public class RequestError {

    private final String message;

    public RequestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
