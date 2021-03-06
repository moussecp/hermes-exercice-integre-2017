package com.hermes_ecs.java_exercise.dao;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise.domain.constant.Constant;
import com.hermes_ecs.java_exercise.domain.constant.ConstantKey;
import org.springframework.stereotype.Repository;


@Repository
public class ConstantJpaDao extends AbstractJpaDao<ConstantKey, Constant> implements ConstantDao {

    public ConstantJpaDao() {
        super(Constant.class);
    }

    @Override
    public Optional<Constant> findByKey(ConstantKey constantKey) {
        return find(constantKey);
    }

    @Override
    public Optional<Constant> findByKeyName(String constantKeyName) {
        return findByKey(ConstantKey.valueOf(constantKeyName));
    }

}
