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

    /** @see IntegerValidations#eq(Integer, String) */
    default Validation<Double> eq(Double eq, String message) {
        return value -> {
            if (Objects.equals(value, eq)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#min(Integer, String) */
    default Validation<Double> min(Double min, String message) {
        return value -> {
            if (null == value || value >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#max(Integer, String) */
    default Validation<Double> max(Double max, String message) {
        return value -> {
            if (null == value || value <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#between(Integer, Integer, String) */
    default Validation<Double> between(Double min, Double max, String message) {
        return value -> {
            if (null == value || (value >= min && value <= max)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#gt(Integer, String) */
    default Validation<Double> gt(Double threshold, String message) {
        return value -> {
            if (null == value || value > threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#lt(Integer, String) */
    default Validation<Double> lt(Double threshold, String message) {
        return value -> {
            if (null == value || value < threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

}
