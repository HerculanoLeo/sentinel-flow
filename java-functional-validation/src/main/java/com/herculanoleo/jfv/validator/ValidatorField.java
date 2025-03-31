package com.herculanoleo.jfv.validator;

import com.herculanoleo.jfv.models.Field;
import com.herculanoleo.jfv.models.Result;
import com.herculanoleo.jfv.validations.Validation;
import org.apache.commons.lang3.StringUtils;

public interface ValidatorField<V> {

    Field<V> getField();

    void addValidation(Validation<V> validation);

    Result<V> build();

    default boolean equalsField(ValidatorField<?> obj) {
        return StringUtils.equals(getField().name(), obj.getField().name());
    }

}
