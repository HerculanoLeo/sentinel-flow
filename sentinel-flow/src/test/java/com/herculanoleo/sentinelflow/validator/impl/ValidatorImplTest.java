package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.sentinelflow.exceptions.ValidatorFieldAddedException;
import com.herculanoleo.sentinelflow.mock.UserMock;
import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.models.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ValidatorImplTest {

    private ValidatorImpl<UserMock> validator;

    private final UserMock userMock = UserMock.johnDoe();

    @BeforeEach
    public void setUp() {
        this.validator = spy(new ValidatorImpl<>(userMock));
    }

    @Test
    public void test_field_with_value() {
        var add = validator.field("name", UserMock::name);
        assertNotNull(add);
        assertEquals(ValidatorAddValidationImpl.class, add.getClass());
        verify(validator).field(eq(new Field<>("name", userMock.name())));
    }

    @Test
    public void test_field_NullPointerException() {
        var add = validator.field("name", (value) -> {
            throw new NullPointerException();
        });
        assertNotNull(add);
        assertEquals(ValidatorAddValidationImpl.class, add.getClass());
        verify(validator).field(eq(new Field<>("name", null)));
    }

    @Test
    public void test_field_NoSuchElementException() {
        var add = validator.field("name", (value) -> {
            throw new NoSuchElementException();
        });
        assertNotNull(add);
        assertEquals(ValidatorAddValidationImpl.class, add.getClass());
        verify(validator).field(eq(new Field<>("name", null)));
    }

    @Test
    public void test_addValidatorField() {
        var validatorField = new ValidatorFieldImpl<>(new Field<>("name", userMock.name()));
        this.validator.addValidatorField(validatorField);
        assertTrue(this.validator.getValidatorFields().contains(validatorField));
    }

    @Test
    public void test_addValidatorField_ValidatorFieldAddedException() {
        var validatorField = new ValidatorFieldImpl<>(new Field<>("name", userMock.name()));
        this.validator.addValidatorField(validatorField);
        assertTrue(this.validator.getValidatorFields().contains(validatorField));
        assertThrows(ValidatorFieldAddedException.class, () -> this.validator.addValidatorField(
                new ValidatorFieldImpl<>(
                        new Field<>("name", userMock.name())
                )
        ));
    }

    @Test
    public void test_validate() throws ValidatorException {
        this.validator.field("name", UserMock::name)
                .add(value -> new ValidationResult(true, null))
                .end()
                .field("lastName", UserMock::lastName)
                .add(value -> new ValidationResult(true, null))
                .end();

        validator.validate();
    }

    @Test
    public void test_validate_ValidatorException() {
        this.validator.field("name", UserMock::name)
                .add(value -> new ValidationResult(true, null))
                .end()
                .field("lastName", UserMock::lastName)
                .add(value -> new ValidationResult(true, null))
                .end()
                .field("birthday", UserMock::birthday)
                .add(value -> new ValidationResult(true, null))
                .add(value -> new ValidationResult(false, "Invalid date"))
                .end();

        assertThrows(ValidatorException.class, () -> {
            try {
                validator.validate();
            } catch (ValidatorException e) {
                assertEquals("there are invalid fields", e.getMessage());
                var fieldErrors = e.getFieldErrors().stream().toList();
                assertEquals(1, fieldErrors.size());
                assertEquals("birthday", fieldErrors.get(0).fieldName());
                assertEquals("Invalid date", fieldErrors.get(0).messages().stream().toList().get(0));
                throw e;
            }
        });
    }


}
