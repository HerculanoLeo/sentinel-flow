package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.util.Objects;

/**
 * Validation rules for {@link Double} values.
 *
 * <p>{@code null} is valid for all rules except {@link #eq(Double, String)}.
 */
public interface DoubleValidations extends ValidationSupport {

    /**
     * Validates that the value is equal to the expected value.
     *
     * @param eq      expected value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Double> eq(Double eq, String message) {
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
    default Validation<Double> min(Double min, String message) {
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
    default Validation<Double> max(Double max, String message) {
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
    default Validation<Double> between(Double min, Double max, String message) {
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
    default Validation<Double> gt(Double threshold, String message) {
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
    default Validation<Double> lt(Double threshold, String message) {
        return value -> {
            if (null == value || value < threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

}
