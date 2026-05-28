package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

/**
 * Generic validation rules for {@link Comparable} types.
 *
 * <p>Useful for {@link java.time.OffsetDateTime}, {@link java.time.Instant},
 * {@link java.time.ZonedDateTime}, and similar types.
 */
public interface ComparableValidations extends ValidationSupport {

    /** Validates that the value is equal to the expected value. */
    default <T extends Comparable<T>> Validation<T> comparableEq(T eq, String message) {
        return value -> {
            if (null != value && eq.compareTo(value) == 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the value is greater than or equal to the minimum. */
    default <T extends Comparable<T>> Validation<T> comparableMin(T min, String message) {
        return value -> {
            if (null == value || value.compareTo(min) >= 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the value is less than or equal to the maximum. */
    default <T extends Comparable<T>> Validation<T> comparableMax(T max, String message) {
        return value -> {
            if (null == value || value.compareTo(max) <= 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the value is within the inclusive range. */
    default <T extends Comparable<T>> Validation<T> comparableBetween(T min, T max, String message) {
        return value -> {
            if (null == value || (value.compareTo(min) >= 0 && value.compareTo(max) <= 0)) {
                return valid();
            }
            return invalid(message);
        };
    }

}
