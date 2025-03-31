package com.herculanoleo.jfv.validator;

import com.herculanoleo.jfv.exceptions.ValidatorException;

import java.util.function.Function;

public interface Validator<V> {

    <R> ValidatorAddValidation<V, R> field(String fieldName, Function<V, R> capture);

    <R> void addValidatorField(ValidatorField<R> validatorField);

    void validate() throws ValidatorException;

}
