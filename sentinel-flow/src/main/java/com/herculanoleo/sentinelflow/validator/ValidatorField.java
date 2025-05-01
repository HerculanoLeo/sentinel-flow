package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.models.Result;
import com.herculanoleo.sentinelflow.validations.Validation;
import org.apache.commons.lang3.StringUtils;

/**
 * The `ValidatorField` interface defines the contract for a field validator, which is responsible for managing and validating fields of type V.
 */
public interface ValidatorField<V> {

    /**
     * Returns the {@link Field} object that this validator is working on.
     *
     * @return The {@link Field} instance associated with this validator.
     */
    Field<V> getField();

    /**
     * Adds a validation to be performed on the field managed by this validator.
     *
     * @param validation The {@link Validation} object that defines the validation rules.
     */
    void addValidation(Validation<V> validation);

    /**
     * Builds and returns the result of the validations applied to the field.
     *
     * @return A {@link Result} object containing the outcome of all validations.
     */
    Result<V> build();

    /**
     * Compares this validator's field name with another validator's field name for equality.
     *
     * @param obj The other {@link ValidatorField} to compare against.
     * @return True if the field names are equal, false otherwise.
     */
    default boolean equalsField(ValidatorField<?> obj) {
        return StringUtils.equals(getField().name(), obj.getField().name());
    }
}
