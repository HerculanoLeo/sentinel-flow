package com.herculanoleo.jfv.validator;

import com.herculanoleo.jfv.exceptions.ValidatorException;
import com.herculanoleo.jfv.exceptions.ValidatorFieldAddedException;
import com.herculanoleo.jfv.models.Field;
import com.herculanoleo.jfv.models.ValidationResult;
import com.herculanoleo.jfv.models.ValidatorFieldErrorMessages;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public abstract class ValidatorAbstraction<E> implements Validator<E> {

    private final E value;

    private final List<ValidatorField<?>> validatorFields = new LinkedList<>();

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
