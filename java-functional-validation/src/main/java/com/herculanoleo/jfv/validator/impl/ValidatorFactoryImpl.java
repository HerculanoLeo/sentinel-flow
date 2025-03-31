package com.herculanoleo.jfv.validator.impl;

import com.herculanoleo.jfv.validator.ValidatorFactory;
import com.herculanoleo.jfv.validator.Validator;

public class ValidatorFactoryImpl implements ValidatorFactory {

    @Override
    public <V> Validator<V> create(V value) {
        return new ValidatorImpl<>(value);
    }

}
