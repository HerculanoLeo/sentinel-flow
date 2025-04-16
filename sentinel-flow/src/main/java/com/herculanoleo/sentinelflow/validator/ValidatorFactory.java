package com.herculanoleo.sentinelflow.validator;

public interface ValidatorFactory {

    <V> Validator<V> create(V value);

}
