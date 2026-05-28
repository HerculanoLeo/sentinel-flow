package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.time.LocalDateTime;

/**
 * Validation rules for {@link LocalDateTime} values.
 */
public interface LocalDateTimeValidations extends ValidationSupport {

    /** Validates that the date-time is equal to the expected value. */
    default Validation<LocalDateTime> eq(LocalDateTime eq, String message) {
        return value -> {
            if (null != value && eq.isEqual(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the date-time is on or after the minimum. */
    default Validation<LocalDateTime> min(LocalDateTime min, String message) {
        return value -> {
            if (null == value || value.isAfter(min) || value.isEqual(min)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the date-time is on or before the maximum. */
    default Validation<LocalDateTime> max(LocalDateTime max, String message) {
        return value -> {
            if (null == value || value.isBefore(max) || value.isEqual(max)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the date-time is within the inclusive range. */
    default Validation<LocalDateTime> between(LocalDateTime min, LocalDateTime max, String message) {
        return value -> {
            if (null == value || (!value.isBefore(min) && !value.isAfter(max))) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the date-time is before now. */
    default Validation<LocalDateTime> isPastDateTime(String message) {
        return value -> {
            if (null == value || value.isBefore(LocalDateTime.now())) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the date-time is after now. */
    default Validation<LocalDateTime> isFutureDateTime(String message) {
        return value -> {
            if (null == value || value.isAfter(LocalDateTime.now())) {
                return valid();
            }
            return invalid(message);
        };
    }

}
