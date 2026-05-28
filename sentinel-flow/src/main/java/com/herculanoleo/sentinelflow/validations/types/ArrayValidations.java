package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

/**
 * Validation rules for array values.
 */
public interface ArrayValidations extends ValidationSupport {

    /**
     * Validates the exact array length.
     *
     * @param <T>     array element type
     * @param size    expected length
     * @param message error message when validation fails
     * @return validation rule
     */
    default <T> Validation<T[]> arraySizeEq(Integer size, String message) {
        return value -> {
            if (null == value || value.length == size) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the minimum array length.
     *
     * @param <T>     array element type
     * @param min     minimum length
     * @param message error message when validation fails
     * @return validation rule
     */
    default <T> Validation<T[]> arraySizeMin(Integer min, String message) {
        return value -> {
            if (null == value || value.length >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the maximum array length.
     *
     * @param <T>     array element type
     * @param max     maximum length
     * @param message error message when validation fails
     * @return validation rule
     */
    default <T> Validation<T[]> arraySizeMax(Integer max, String message) {
        return value -> {
            if (null == value || value.length <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

}
