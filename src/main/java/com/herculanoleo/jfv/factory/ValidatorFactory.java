package com.herculanoleo.jfv.factory;

import com.herculanoleo.jfv.validator.Validator;

public interface ValidatorFactory {

    <V> Validator<V> create(V value);

}
