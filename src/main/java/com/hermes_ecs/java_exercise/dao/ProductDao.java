package com.hermes_ecs.java_exercise.dao;

import com.hermes_ecs.java_exercise.domain.Product;

import java.util.List;

public interface ProductDao extends Dao<Long, Product> {
    boolean existsWithSameLabel(String label);

    boolean existsDifferentEntityWithSameLabel(Product product, String label);

    List<Product> findAllProductsOrganized();
}
