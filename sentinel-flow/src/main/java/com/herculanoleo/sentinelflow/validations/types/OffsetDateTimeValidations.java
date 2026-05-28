package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;

import java.time.OffsetDateTime;

/**
 * Validation rules for {@link OffsetDateTime} values.
 */
public interface OffsetDateTimeValidations extends ComparableValidations {

    /** Validates that the date-time is equal to the expected value. */
    default Validation<OffsetDateTime> eq(OffsetDateTime eq, String message) {
        return comparableEq(eq, message);
    }

    /** Validates that the date-time is on or after the minimum. */
    default Validation<OffsetDateTime> min(OffsetDateTime min, String message) {
        return comparableMin(min, message);
    }

    /** Validates that the date-time is on or before the maximum. */
    default Validation<OffsetDateTime> max(OffsetDateTime max, String message) {
        return comparableMax(max, message);
    }

    /** Validates that the date-time is before now. */
    default Validation<OffsetDateTime> isPastOffsetDateTime(String message) {
        return value -> {
            if (null == value || value.isBefore(OffsetDateTime.now())) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the date-time is after now. */
    default Validation<OffsetDateTime> isFutureOffsetDateTime(String message) {
        return value -> {
            if (null == value || value.isAfter(OffsetDateTime.now())) {
                return valid();
            }
            return invalid(message);
        };
    }

}
