package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Validation rules for generic objects.
 */
public interface ObjectValidations extends ValidationSupport {

    /**
     * Validates that the value is not {@code null}.
     *
     * @param message error message when validation fails
     * @param <T>     value type
     * @return validation rule
     */
    default <T> Validation<T> isNotNull(String message) {
        return value -> {
            if (null != value) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is {@code null}.
     *
     * @param message error message when validation fails
     * @param <T>     value type
     * @return validation rule
     */
    default <T> Validation<T> isNull(String message) {
        return value -> {
            if (null == value) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is an instance of the given class.
     *
     * @param clazz   expected class
     * @param message error message when validation fails
     * @param <T>     value type
     * @return validation rule
     */
    default <T> Validation<T> isClass(Class<?> clazz, String message) {
        return value -> {
            if (clazz.isAssignableFrom(value.getClass())) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the value is contained in the allowed set.
     *
     * <p>{@code null} values are considered valid.
     *
     * @param allowed allowed values
     * @param message error message when validation fails
     * @param <T>     value type
     * @return validation rule
     */
    default <T> Validation<T> oneOf(Set<T> allowed, String message) {
        return value -> {
            if (null == value || (null != allowed && allowed.contains(value))) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the value using a custom predicate.
     *
     * @param predicate validation logic
     * @param message   error message when validation fails
     * @param <T>       value type
     * @return validation rule
     */
    default <T> Validation<T> custom(Predicate<T> predicate, String message) {
        return value -> {
            if (predicate.test(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

}
