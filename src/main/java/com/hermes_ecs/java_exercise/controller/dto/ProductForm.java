package com.hermes_ecs.java_exercise.controller.dto;

import com.hermes_ecs.java_exercise.domain.Product;
import com.hermes_ecs.java_exercise.domain.RepublicDactaryAmount;

public class ProductForm {

    private Long id;
    private String label;
    private String description;
    private String price;

    public ProductForm() {
    }

    public ProductForm(Product product) {
        this.id = product.getId();
        this.label = product.getLabel();
        this.description = product.getDescription();
        this.price = product.getPrice().getValueForDisplay();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RepublicDactaryAmount getPriceAsRepublicDactaryAmount() {
        return new RepublicDactaryAmount(price);
    }
}
