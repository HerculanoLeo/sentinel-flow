package com.herculanoleo.sentinelflow.validations;

import com.herculanoleo.sentinelflow.validations.types.ArrayValidations;
import com.herculanoleo.sentinelflow.validations.types.BigDecimalValidations;
import com.herculanoleo.sentinelflow.validations.types.BooleanValidations;
import com.herculanoleo.sentinelflow.validations.types.BrazilValidations;
import com.herculanoleo.sentinelflow.validations.types.CollectionValidations;
import com.herculanoleo.sentinelflow.validations.types.ComparableValidations;
import com.herculanoleo.sentinelflow.validations.types.DoubleValidations;
import com.herculanoleo.sentinelflow.validations.types.IntegerValidations;
import com.herculanoleo.sentinelflow.validations.types.LocalDateTimeValidations;
import com.herculanoleo.sentinelflow.validations.types.LocalDateValidations;
import com.herculanoleo.sentinelflow.validations.types.LocalTimeValidations;
import com.herculanoleo.sentinelflow.validations.types.LongValidations;
import com.herculanoleo.sentinelflow.validations.types.MapValidations;
import com.herculanoleo.sentinelflow.validations.types.ObjectValidations;
import com.herculanoleo.sentinelflow.validations.types.OffsetDateTimeValidations;
import com.herculanoleo.sentinelflow.validations.types.StringValidations;

/**
 * Factory for all built-in validation rules.
 *
 * <p>Aggregates type-specific validation interfaces into a single entry point.
 * Use with the validator fluent API via {@code .add(validations.rule(...))}.
 *
 * @see com.herculanoleo.sentinelflow.validations.impl.ValidationFactoryImpl
 */
public interface ValidationFactory extends
        ObjectValidations,
        StringValidations,
        BooleanValidations,
        IntegerValidations,
        LongValidations,
        DoubleValidations,
        BigDecimalValidations,
        LocalDateValidations,
        LocalTimeValidations,
        LocalDateTimeValidations,
        OffsetDateTimeValidations,
        ComparableValidations,
        CollectionValidations,
        MapValidations,
        ArrayValidations,
        BrazilValidations,
        ValidationSupport {
}
