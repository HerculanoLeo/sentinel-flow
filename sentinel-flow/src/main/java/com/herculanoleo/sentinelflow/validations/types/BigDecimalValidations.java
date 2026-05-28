package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.math.BigDecimal;

/**
 * Validation rules for {@link BigDecimal} values.
 *
 * <p>{@code null} is valid for all rules except {@link #eq(BigDecimal, String)}.
 */
public interface BigDecimalValidations extends ValidationSupport {

    /**
     * Validates that the value is equal to the expected value.
     *
     * @param eq      expected value
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<BigDecimal> eq(BigDecimal eq, String message) {
        return value -> {
            if (null != value && eq.compareTo(value) == 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#min(Integer, String) */
    default Validation<BigDecimal> min(BigDecimal min, String message) {
        return value -> {
            if (null == value || value.compareTo(min) >= 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#max(Integer, String) */
    default Validation<BigDecimal> max(BigDecimal max, String message) {
        return value -> {
            if (null == value || value.compareTo(max) <= 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#between(Integer, Integer, String) */
    default Validation<BigDecimal> between(BigDecimal min, BigDecimal max, String message) {
        return value -> {
            if (null == value || (value.compareTo(min) >= 0 && value.compareTo(max) <= 0)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#gt(Integer, String) */
    default Validation<BigDecimal> gt(BigDecimal threshold, String message) {
        return value -> {
            if (null == value || value.compareTo(threshold) > 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#lt(Integer, String) */
    default Validation<BigDecimal> lt(BigDecimal threshold, String message) {
        return value -> {
            if (null == value || value.compareTo(threshold) < 0) {
                return valid();
            }
            return invalid(message);
        };
    }

}
