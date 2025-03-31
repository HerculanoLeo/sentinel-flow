package com.herculanoleo.jfv.validator;

import com.herculanoleo.jfv.validations.Validation;

public interface ValidatorAddValidation<E, V> {

    ValidatorAddValidation<E, V> add(Validation<V> validation);

    Validator<E> end();

}
