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

public abstract class ValidatorAbstraction<E> implements Validator<E> {

    protected final E value;

    protected final List<ValidatorField<?>> validatorFields = new LinkedList<>();

    public ValidatorAbstraction(E value) {
        this.value = value;
    }

    public <V> ValidatorAddValidation<E, V> field(String fieldName, Function<E, V> capture) {
        try {
            return field(new Field<>(fieldName, capture.apply(value)));
        } catch (NullPointerException | NoSuchElementException ignored) {
            return field(new Field<>(fieldName, null));
        }
    }

    public abstract <V> ValidatorAddValidation<E, V> field(Field<V> field);

    public <V> void addValidatorField(ValidatorField<V> validatorField) {
        var fieldOp = this.validatorFields.stream()
                .filter(v -> v.equalsField(validatorField))
                .findFirst();

        if (fieldOp.isPresent()) {
            throw new ValidatorFieldAddedException(validatorField.getField());
        }

        validatorFields.add(validatorField);
    }

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
