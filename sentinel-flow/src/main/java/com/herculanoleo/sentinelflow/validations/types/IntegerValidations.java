package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.util.Objects;

/**
 * Validation rules for {@link Integer} values.
 *
 * <p>{@code null} is valid for all rules except {@link #eq(Integer, String)}.
 */
public interface IntegerValidations extends ValidationSupport {

    /**
     * Validates that the value is equal to the expected value.
     *
     * @param eq      expected value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Integer> eq(Integer eq, String message) {
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
    default Validation<Integer> min(Integer min, String message) {
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
    default Validation<Integer> max(Integer max, String message) {
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
    default Validation<Integer> between(Integer min, Integer max, String message) {
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
    default Validation<Integer> gt(Integer threshold, String message) {
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
    default Validation<Integer> lt(Integer threshold, String message) {
        return value -> {
            if (null == value || value < threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is greater than zero.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Integer> positive(String message) {
        return gt(0, message);
    }

    /**
     * Validates that the value is less than zero.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Integer> negative(String message) {
        return lt(0, message);
    }

    /**
     * Validates that the value is equal to zero.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<Integer> zero(String message) {
        return eq(0, message);
    }

}
