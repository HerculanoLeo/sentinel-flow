package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;

import java.time.LocalTime;

/**
 * Validation rules for {@link LocalTime} values.
 */
public interface LocalTimeValidations extends ComparableValidations {

    /** Validates that the time is equal to the expected value. */
    default Validation<LocalTime> eq(LocalTime eq, String message) {
        return value -> {
            if (eq.equals(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the time is on or after the minimum. */
    default Validation<LocalTime> min(LocalTime min, String message) {
        return comparableMin(min, message);
    }

    /** Validates that the time is on or before the maximum. */
    default Validation<LocalTime> max(LocalTime max, String message) {
        return comparableMax(max, message);
    }

}
