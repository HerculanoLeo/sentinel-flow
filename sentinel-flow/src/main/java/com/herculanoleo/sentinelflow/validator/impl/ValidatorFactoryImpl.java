package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.sentinelflow.validator.Validator;

public class ValidatorFactoryImpl implements ValidatorFactory {

    @Override
    public <V> Validator<V> create(V value) {
        return new ValidatorImpl<>(value);
    }

}
