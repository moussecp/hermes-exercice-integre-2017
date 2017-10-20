package com.hermes_ecs.java_exercise.dao;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise.domain.constant.Constant;
import com.hermes_ecs.java_exercise.domain.constant.ConstantKey;

public interface ConstantDao extends Dao<ConstantKey, Constant> {
    Optional<Constant> findByKey(ConstantKey constantKey);

    Optional<Constant> findByKeyName(String constantKeyName);
}
