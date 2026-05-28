package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.util.Objects;

/**
 * Validation rules for {@link Long} values.
 *
 * <p>{@code null} is valid for all rules except {@link #eq(Long, String)}.
 */
public interface LongValidations extends ValidationSupport {

    /**
     * Validates that the value is equal to the expected value.
     *
     * @param eq      expected value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Long> eq(Long eq, String message) {
        return value -> {
            if (Objects.equals(value, eq)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is greater than or equal to the minimum.
     *
     * @param min     minimum inclusive value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Long> min(Long min, String message) {
        return value -> {
            if (null == value || value >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is less than or equal to the maximum.
     *
     * @param max     maximum inclusive value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Long> max(Long max, String message) {
        return value -> {
            if (null == value || value <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is within the inclusive range.
     *
     * @param min     minimum inclusive value
     * @param max     maximum inclusive value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Long> between(Long min, Long max, String message) {
        return value -> {
            if (null == value || (value >= min && value <= max)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is greater than the threshold.
     *
     * @param threshold threshold value
     * @param message   error message when validation fails
     * @return validation rule
     */
    default Validation<Long> gt(Long threshold, String message) {
        return value -> {
            if (null == value || value > threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is less than the threshold.
     *
     * @param threshold threshold value
     * @param message   error message when validation fails
     * @return validation rule
     */
    default Validation<Long> lt(Long threshold, String message) {
        return value -> {
            if (null == value || value < threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

}
