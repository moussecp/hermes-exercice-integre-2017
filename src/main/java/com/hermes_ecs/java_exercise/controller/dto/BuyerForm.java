package com.hermes_ecs.java_exercise.controller.dto;

import com.hermes_ecs.java_exercise.domain.Buyer;

public class BuyerForm {

    private Long id;

    private String firstName;
    private String lastName;
    private String birthLocation;

    public BuyerForm() {
    }

    public BuyerForm(Buyer buyer) {
        this.id = buyer.getId();
        this.firstName = buyer.getFirstName();
        this.lastName = buyer.getLastName();
        this.birthLocation = buyer.getBirthLocation();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
