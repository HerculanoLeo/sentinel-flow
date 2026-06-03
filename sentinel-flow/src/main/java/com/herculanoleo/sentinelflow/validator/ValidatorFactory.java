package com.herculanoleo.sentinelflow.validator;

/**
 * Factory for creating {@link Validator} instances.
 *
 * @see com.herculanoleo.sentinelflow.validator.impl.ValidatorFactoryImpl
 */
public interface ValidatorFactory {

    /**
     * Creates a validator for the given object.
     *
     * @param value object to validate
     * @param <V>   object type
     * @return validator bound to the given value
     */
    <V> Validator<V> create(V value);

}
