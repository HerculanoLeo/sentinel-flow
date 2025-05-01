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
 * The `ValidatorAbstraction` class provides a skeletal implementation of the {@link Validator} interface, serving as a base for concrete validator implementations.
 * It encapsulates common functionality required by most validators, such as storing the value to be validated and managing fields for validation.
 *
 * @param <E> The type of the entity being validated.
 */
public abstract class ValidatorAbstraction<E> implements Validator<E> {
    /**
     * The value to be validated.
     */
    protected final E value;

    /**
     * A list of validator fields that will be used during the validation process.
     */
    protected final List<ValidatorField<?>> validatorFields = new LinkedList<>();

    /**
     * Constructs a new ValidatorAbstraction with the specified value to validate.
     *
     * @param value The value to be validated.
     */
    public ValidatorAbstraction(E value) {
        this.value = value;
    }

    /**
     * Adds a field for validation using a lambda expression to capture the field's value from the main object.
     * If there is an issue capturing the field value (e.g., null pointer), it will default to null.
     *
     * @param fieldName The name of the field.
     * @param capture   A function that captures the field value from the main object.
     * @return A builder for adding more details to this field's validator.
     */
    public <V> ValidatorAddValidation<E, V> field(String fieldName, Function<E, V> capture) {
        try {
            return field(new Field<>(fieldName, capture.apply(value)));
        } catch (NullPointerException | NoSuchElementException ignored) {
            return field(new Field<>(fieldName, null));
        }
    }

    /**
     * Abstract method to add a specific field for validation. Implementing classes must provide an implementation.
     *
     * @param field The field object containing the name and initial value.
     * @return A builder for adding more details to this field's validator.
     */
    public abstract <V> ValidatorAddValidation<E, V> field(Field<V> field);

    /**
     * Adds a new validator field to the list of fields to be validated.
     *
     * @param validatorField The validator field object to add.
     * @throws ValidatorFieldAddedException if a field with the same name already exists.
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
     * Validates all the fields added to this abstraction. If any field is invalid, it throws a ValidatorException.
     *
     * @throws ValidatorException if there are any invalid fields.
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
