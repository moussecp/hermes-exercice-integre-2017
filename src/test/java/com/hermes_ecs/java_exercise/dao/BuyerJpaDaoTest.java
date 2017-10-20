package com.hermes_ecs.java_exercise.dao;

import com.hermes_ecs.java_exercise.domain.Buyer;
import org.springframework.beans.factory.annotation.Autowired;

public class BuyerJpaDaoTest extends AbstractJpaDaoTest<Long, Buyer> {

    @Autowired
    private BuyerDao buyerDao;

    @Override
    protected BuyerDao getDao() {
        return buyerDao;
    }

    @Override
    protected Buyer buildItem(int i) {
        return new Buyer("Luke" + i, "Skywalker", "Tatouine");
    }
}
