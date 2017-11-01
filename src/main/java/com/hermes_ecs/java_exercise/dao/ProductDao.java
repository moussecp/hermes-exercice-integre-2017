package com.hermes_ecs.java_exercise.dao;

import com.hermes_ecs.java_exercise.domain.Product;
import com.hermes_ecs.java_exercise.domain.constant.Department;

import java.util.List;

public interface ProductDao extends Dao<Long, Product> {
    boolean existsWithSameLabel(String label);

    boolean existsDifferentEntityWithSameLabel(Product product, String label);

    List<Product> findAllProductsOrganized();

    List<Product> getProductsWithLabelLike(String labelName);

    List<Product> getProductsWithCategoryName(String categoryName);

    List<Product> getProductsWithCategoryDepartment(Department department);
}
