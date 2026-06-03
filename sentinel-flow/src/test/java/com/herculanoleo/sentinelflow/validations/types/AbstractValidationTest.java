package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.impl.ValidationFactoryImpl;
import org.junit.jupiter.api.BeforeEach;

abstract class AbstractValidationTest {

    protected ValidationFactoryImpl validationFactory;

    @BeforeEach
    void setup() {
        validationFactory = new ValidationFactoryImpl();
    }

}
