package com.hermes_ecs.java_exercise.dao;

import com.hermes_ecs.java_exercise.domain.Product;

public class ProductMapDao extends AbstractMapDao<Product> implements ProductDao {
    @Override
    public boolean existsWithSameLabel(String label) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean existsDifferentEntityWithSameLabel(Product product, String label) {
        throw new UnsupportedOperationException("not implemented");
    }
}
