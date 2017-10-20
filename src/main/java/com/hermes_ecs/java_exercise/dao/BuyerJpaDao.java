package com.hermes_ecs.java_exercise.dao;

import com.hermes_ecs.java_exercise.domain.Buyer;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerJpaDao extends AbstractJpaDao<Long, Buyer> implements BuyerDao {

    BuyerJpaDao() {
        super(Buyer.class);
    }

}
