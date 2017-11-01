package com.hermes_ecs.java_exercise.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/api/hello", produces = "application/json")
    public Message greet() {
        return new Message("Hello from the other side!!!");
    }

    class Message {
        String field;
        Message(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }
}
