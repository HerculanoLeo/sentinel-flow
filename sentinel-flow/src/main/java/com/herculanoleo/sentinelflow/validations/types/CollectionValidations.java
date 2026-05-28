package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;

import java.util.Collection;

/**
 * Validation rules for {@link Collection} values.
 */
public interface CollectionValidations extends ValidationSupport {

    /** Validates the exact collection size. */
    default <T extends Collection<?>> Validation<T> sizeEq(Integer size, String message) {
        return value -> {
            if (null == value || value.size() == size) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates the minimum collection size. */
    default <T extends Collection<?>> Validation<T> sizeMin(Integer min, String message) {
        return value -> {
            if (null == value || value.size() >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates the maximum collection size. */
    default <T extends Collection<?>> Validation<T> sizeMax(Integer max, String message) {
        return value -> {
            if (null == value || value.size() <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the collection is empty. */
    default <T extends Collection<?>> Validation<T> isEmpty(String message) {
        return value -> {
            if (null == value || value.isEmpty()) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the collection is not empty. */
    default <T extends Collection<?>> Validation<T> isNotEmpty(String message) {
        return value -> {
            if (null == value || !value.isEmpty()) {
                return valid();
            }
            return invalid(message);
        };
    }

    /** Validates that the collection contains the given element. */
    default <C extends Collection<E>, E> Validation<C> contains(E element, String message) {
        return value -> {
            if (null == value || value.contains(element)) {
                return valid();
            }
            return invalid(message);
        };
    }

}
