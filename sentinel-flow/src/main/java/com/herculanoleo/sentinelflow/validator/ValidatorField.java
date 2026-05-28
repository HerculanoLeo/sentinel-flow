package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.models.Result;
import com.herculanoleo.sentinelflow.validations.Validation;
import org.apache.commons.lang3.StringUtils;

/**
 * A field with an ordered list of validation rules.
 *
 * @param <V> field value type
 */
public interface ValidatorField<V> {

    /**
     * Returns the field metadata and captured value.
     *
     * @return the field metadata and captured value
     */
    Field<V> getField();

    /**
     * Adds a validation rule to this field.
     *
     * @param validation rule to apply
     */
    void addValidation(Validation<V> validation);

    /**
     * Runs all rules and returns the aggregated result.
     *
     * @return aggregated validation result for this field
     */
    Result<V> build();

    /**
     * Compares two fields by name.
     *
     * @param obj other field validator
     * @return {@code true} when field names are equal
     */
    default boolean equalsField(ValidatorField<?> obj) {
        return StringUtils.equals(getField().name(), obj.getField().name());
    }

}
