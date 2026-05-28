package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.time.LocalDate;

/**
 * Validation rules for {@link LocalDate} values.
 */
public interface LocalDateValidations extends ValidationSupport {

    /**
     * Validates that the date is equal to the expected value.
     *
     * @param eq      expected value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<LocalDate> eq(LocalDate eq, String message) {
        return value -> {
            if (null != value && eq.isEqual(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the date is on or after the minimum.
     *
     * @param min     minimum inclusive value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<LocalDate> min(LocalDate min, String message) {
        return value -> {
            if (null == value || value.isAfter(min) || value.isEqual(min)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the date is on or before the maximum.
     *
     * @param max     maximum inclusive value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<LocalDate> max(LocalDate max, String message) {
        return value -> {
            if (null == value || value.isBefore(max) || value.isEqual(max)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the date is within the inclusive range.
     *
     * @param min     minimum inclusive value
     * @param max     maximum inclusive value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<LocalDate> between(LocalDate min, LocalDate max, String message) {
        return value -> {
            if (null == value || (!value.isBefore(min) && !value.isAfter(max))) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the date is before today.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<LocalDate> isPast(String message) {
        return value -> {
            if (null == value || value.isBefore(LocalDate.now())) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the date is after today.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<LocalDate> isFuture(String message) {
        return value -> {
            if (null == value || value.isAfter(LocalDate.now())) {
                return valid();
            }
            return invalid(message);
        };
    }

}
