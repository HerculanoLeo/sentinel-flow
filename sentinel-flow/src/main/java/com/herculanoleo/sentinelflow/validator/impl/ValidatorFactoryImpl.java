package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.sentinelflow.validator.Validator;

/**
 * Default implementation of {@link ValidatorFactory}.
 */
public class ValidatorFactoryImpl implements ValidatorFactory {

    /** Creates a new validator factory. */
    public ValidatorFactoryImpl() {
    }

    @Override
    public <V> Validator<V> create(V value) {
        return new ValidatorImpl<>(value);
    }

}
