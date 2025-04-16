package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.validations.Validation;

public interface ValidatorAddValidation<E, V> {

    ValidatorAddValidation<E, V> add(Validation<V> validation);

    Validator<E> end();

}
