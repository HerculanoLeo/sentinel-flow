package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

/**
 * Validation rules for {@link Boolean} values.
 *
 * <p>{@code null} fails both {@code isTrue} and {@code isFalse}.
 */
public interface BooleanValidations extends ValidationSupport {

    /**
     * Validates that the value is {@link Boolean#TRUE}.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Boolean> isTrue(String message) {
        return value -> {
            if (Boolean.TRUE.equals(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is {@link Boolean#FALSE}.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Boolean> isFalse(String message) {
        return value -> {
            if (Boolean.FALSE.equals(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

}
