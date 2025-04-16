package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.models.Result;
import com.herculanoleo.sentinelflow.validations.Validation;
import org.apache.commons.lang3.StringUtils;

public interface ValidatorField<V> {

    Field<V> getField();

    void addValidation(Validation<V> validation);

    Result<V> build();

    default boolean equalsField(ValidatorField<?> obj) {
        return StringUtils.equals(getField().name(), obj.getField().name());
    }

}
