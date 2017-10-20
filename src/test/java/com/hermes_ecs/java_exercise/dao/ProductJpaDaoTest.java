package com.hermes_ecs.java_exercise.dao;

import com.hermes_ecs.java_exercise.domain.Product;
import com.hermes_ecs.java_exercise.domain.RepublicDactaryAmount;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductJpaDaoTest extends AbstractJpaDaoTest<Long, Product> {
    @Autowired
    private ProductDao dao;

    @Override
    protected Product buildItem(int i) {
        final RepublicDactaryAmount price = new RepublicDactaryAmount("123");
        return new Product("Landspeeder" + i, null, "Incredibly fast kart", price);
    }

    @Override
    protected ProductDao getDao() {
        return dao;
    }
}
