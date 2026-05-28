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

    /** @see IntegerValidations#eq(Integer, String) */
    default Validation<Long> eq(Long eq, String message) {
        return value -> {
            if (Objects.equals(value, eq)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#min(Integer, String) */
    default Validation<Long> min(Long min, String message) {
        return value -> {
            if (null == value || value >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#max(Integer, String) */
    default Validation<Long> max(Long max, String message) {
        return value -> {
            if (null == value || value <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#between(Integer, Integer, String) */
    default Validation<Long> between(Long min, Long max, String message) {
        return value -> {
            if (null == value || (value >= min && value <= max)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#gt(Integer, String) */
    default Validation<Long> gt(Long threshold, String message) {
        return value -> {
            if (null == value || value > threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** @see IntegerValidations#lt(Integer, String) */
    default Validation<Long> lt(Long threshold, String message) {
        return value -> {
            if (null == value || value < threshold) {
                return valid();
            }
            return invalid(message);
        };
    }

}
