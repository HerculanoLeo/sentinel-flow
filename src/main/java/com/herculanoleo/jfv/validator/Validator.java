package com.herculanoleo.jfv.validator;

import com.herculanoleo.jfv.exceptions.ValidatorException;
import com.herculanoleo.jfv.models.Field;

import java.util.function.Function;

public interface Validator<V> {

    <R> ValidatorAddValidation<V, R> field(String fieldName, Function<V, R> capture);

    <R> ValidatorAddValidation<V, R> field(Field<R> field);

    <R> void addValidatorField(ValidatorField<R> validatorField);

    void validate() throws ValidatorException;

}
