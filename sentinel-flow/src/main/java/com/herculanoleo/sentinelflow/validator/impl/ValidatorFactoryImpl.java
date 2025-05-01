package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.sentinelflow.validator.Validator;

/**
 * The `ValidatorFactoryImpl` class is a concrete implementation of the {@link ValidatorFactory} interface.
 * It provides a method to create instances of {@link Validator} for specific values.
 */
public class ValidatorFactoryImpl implements ValidatorFactory {

    @Override
    public <V> Validator<V> create(V value) {
        return new ValidatorImpl<>(value);
    }

}
