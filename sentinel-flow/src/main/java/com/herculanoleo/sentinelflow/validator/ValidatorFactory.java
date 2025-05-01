/**
 * The `ValidatorFactory` interface provides a contract for creating instances of {@link Validator} objects.
 * This factory pattern allows for the creation of validator objects that can validate specific values.
 */
package com.herculanoleo.sentinelflow.validator;

public interface ValidatorFactory {

    /**
     * Creates a new instance of {@code Validator<V>} with the specified value to be validated.
     *
     * @param <V>   The type of the value to be validated.
     * @param value The value to be wrapped in a validator.
     * @return A new instance of {@link Validator} for the given value.
     */
    <V> Validator<V> create(V value);

}
