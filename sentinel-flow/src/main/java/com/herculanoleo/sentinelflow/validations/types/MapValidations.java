package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.util.Map;

/**
 * Validation rules for {@link Map} values.
 */
public interface MapValidations extends ValidationSupport {

    /**
     * Validates the exact number of map entries.
     *
     * @param <T>     map type
     * @param size    expected entry count
     * @param message error message when validation fails
     * @return validation rule
     */
    default <T extends Map<?, ?>> Validation<T> mapSizeEq(Integer size, String message) {
        return value -> {
            if (null == value || value.size() == size) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the minimum number of map entries.
     *
     * @param <T>     map type
     * @param min     minimum entry count
     * @param message error message when validation fails
     * @return validation rule
     */
    default <T extends Map<?, ?>> Validation<T> mapSizeMin(Integer min, String message) {
        return value -> {
            if (null == value || value.size() >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the maximum number of map entries.
     *
     * @param <T>     map type
     * @param max     maximum entry count
     * @param message error message when validation fails
     * @return validation rule
     */
    default <T extends Map<?, ?>> Validation<T> mapSizeMax(Integer max, String message) {
        return value -> {
            if (null == value || value.size() <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

}
