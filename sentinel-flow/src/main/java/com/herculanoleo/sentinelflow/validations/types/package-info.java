/**
 * Validation rules grouped by value type.
 *
 * <p>Each interface provides default factory methods that return a {@link com.herculanoleo.sentinelflow.validations.Validation}.
 * These interfaces are composed by {@link com.herculanoleo.sentinelflow.validations.ValidationFactory}.
 *
 * <p>Unless stated otherwise, {@code null} (and blank strings where applicable) is treated as valid
 * to support optional fields. Combine with {@code isNotNull} / {@code isNotBlank} for required fields.
 */
package com.herculanoleo.sentinelflow.validations.types;
