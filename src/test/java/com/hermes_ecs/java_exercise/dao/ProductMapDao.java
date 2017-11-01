package com.hermes_ecs.java_exercise.dao;

import com.hermes_ecs.java_exercise.domain.Product;
import com.hermes_ecs.java_exercise.domain.constant.Department;

import java.util.List;

public class ProductMapDao extends AbstractMapDao<Product> implements ProductDao {
    @Override
    public boolean existsWithSameLabel(String label) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean existsDifferentEntityWithSameLabel(Product product, String label) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<Product> findAllProductsOrganized() {
        return this.findAll();
    }

    @Override
    public List<Product> getProductsWithLabelLike(String labelName) {
        return null;
    }

    @Override
    public List<Product> getProductsWithCategoryName(String categoryName) {
        return null;
    }

    @Override
    public List<Product> getProductsWithCategoryDepartment(Department department) {
        return null;
    }
}
