package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.sentinelflow.exceptions.ValidatorFieldAddedException;
import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.models.ValidationResult;
import com.herculanoleo.sentinelflow.models.ValidatorFieldErrorMessages;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Base implementation for {@link Validator}.
 *
 * <p>Captures field values from the target object, collects field validators,
 * and throws {@link ValidatorException} when validation fails.
 *
 * @param <E> object type being validated
 */
public abstract class ValidatorAbstraction<E> implements Validator<E> {

    /** The object under validation. */
    protected final E value;

    /** Registered field validators in insertion order. */
    protected final List<ValidatorField<?>> validatorFields = new LinkedList<>();

    /**
     * Creates a validator for the given object.
     *
     * @param value object to validate
     */
    public ValidatorAbstraction(E value) {
        this.value = value;
    }

    /**
     * Extracts a field value from the object and starts the fluent validation step.
     *
     * <p>If extraction fails with {@link NullPointerException} or {@link NoSuchElementException},
     * the field value is set to {@code null}.
     */
    public <V> ValidatorAddValidation<E, V> field(String fieldName, Function<E, V> capture) {
        try {
            return field(new Field<>(fieldName, capture.apply(value)));
        } catch (NullPointerException | NoSuchElementException ignored) {
            return field(new Field<>(fieldName, null));
        }
    }

    /**
     * Starts validation for a field with a pre-captured value.
     *
     * @param field field metadata and value
     * @param <V>   field value type
     * @return fluent step to add validation rules
     */
    public abstract <V> ValidatorAddValidation<E, V> field(Field<V> field);

    /**
     * Registers a field validator.
     *
     * @throws ValidatorFieldAddedException when a field with the same name was already added
     */
    public <V> void addValidatorField(ValidatorField<V> validatorField) {
        var fieldOp = this.validatorFields.stream()
                .filter(v -> v.equalsField(validatorField))
                .findFirst();

        if (fieldOp.isPresent()) {
            throw new ValidatorFieldAddedException(validatorField.getField());
        }

        validatorFields.add(validatorField);
    }

    /**
     * Runs all field validations and throws when any rule fails.
     *
     * @throws ValidatorException with per-field error messages
     */
    public void validate() throws ValidatorException {
        var invalids = this.validatorFields.stream()
                .map(ValidatorField::build)
                .filter(result -> !result.results()
                        .stream()
                        .allMatch(ValidationResult::valid)
                )
                .toList();

        if (!invalids.isEmpty()) {
            var errors = invalids.stream()
                    .map(result -> new ValidatorFieldErrorMessages(
                            result.field().name(),
                            result.results().stream()
                                    .filter(r -> !r.valid())
                                    .map(ValidationResult::message)
                                    .toList()
                    ))
                    .toList();
            throw new ValidatorException("there are invalid fields", errors);
        }
    }

}
