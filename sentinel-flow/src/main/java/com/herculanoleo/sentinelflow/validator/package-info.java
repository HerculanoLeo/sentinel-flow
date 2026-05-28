/**
 * Fluent validator API for applying {@link com.herculanoleo.sentinelflow.validations.Validation}
 * rules to object fields.
 *
 * <p>Typical usage:
 * <pre>{@code
 * Validate logic = (validator, validations) -> validator.create(dto)
 *     .field("email", Dto::email)
 *     .add(validations.email("Invalid email"))
 *     .end()
 *     .validate();
 * logic.validate(new ValidatorFactoryImpl(), new ValidationFactoryImpl());
 * }</pre>
 *
 * @see com.herculanoleo.sentinelflow.validator.impl.ValidatorFactoryImpl
 */
package com.herculanoleo.sentinelflow.validator;
