package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.validations.Validation;

/**
 * The `ValidatorAddValidation` interface defines the contract for a validator that can add validations and then build a final {@link Validator}.
 * This interface is generic, allowing it to work with any type of element (E) and value (V).
 */
public interface ValidatorAddValidation<E, V> {

    /**
     * Adds a validation to the validator. Returns itself to allow method chaining.
     *
     * @param validation The {@link Validation} object to be added.
     * @return The current instance of `ValidatorAddValidation` with the added validation.
     */
    ValidatorAddValidation<E, V> add(Validation<V> validation);

    /**
     * Ends the process of adding validations and builds a {@link Validator} from the accumulated validations.
     *
     * @return A new instance of {@link Validator} containing all added validations.
     */
    Validator<E> end();

}
