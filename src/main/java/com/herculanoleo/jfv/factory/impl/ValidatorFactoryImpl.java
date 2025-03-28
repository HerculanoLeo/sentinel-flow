package com.herculanoleo.jfv.factory.impl;

import com.herculanoleo.jfv.factory.ValidatorFactory;
import com.herculanoleo.jfv.validator.Validator;
import com.herculanoleo.jfv.validator.impl.ValidatorImpl;

public class ValidatorFactoryImpl implements ValidatorFactory {

    @Override
    public <V> Validator<V> create(V value) {
        return new ValidatorImpl<>(value);
    }

}
