package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.mock.UserMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ValidatorFactoryImplTest {

    private final ValidatorFactoryImpl validatorFactory = new ValidatorFactoryImpl();

    @Test
    public void create_a_validator_with_type_of_class() {
        var validator = validatorFactory.create(UserMock.johnDoe());

        assertNotNull(validator);
        assertEquals(ValidatorImpl.class, validator.getClass());
    }

}
