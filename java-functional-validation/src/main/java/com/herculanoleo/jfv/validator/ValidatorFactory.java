package com.herculanoleo.jfv.validator;

public interface ValidatorFactory {

    <V> Validator<V> create(V value);

}
