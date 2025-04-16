package com.herculanoleo.springexample.models.validates;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;
import com.herculanoleo.sentinelflow.validator.Validate;
import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.springexample.models.dtos.user.UserRegistrationDTO;

public class UserRegistrationValidate implements Validate {

    private final UserRegistrationDTO dto;

    private UserRegistrationValidate(UserRegistrationDTO dto) {
        this.dto = dto;
    }

    public static UserRegistrationValidate of(UserRegistrationDTO dto) {
        return new UserRegistrationValidate(dto);
    }

    @Override
    public void validate(ValidatorFactory validator, ValidationFactory validation) throws ValidatorException {
        validator.create(dto)
                .field("name", UserRegistrationDTO::name) // Define o campo a ser validado
                .add(validation.isNotBlank("O nome não pode estar em branco.")) // Adiciona regras
                .add(validation.lengthMin(3, "O nome deve ter pelo menos 3 caracteres."))
                .end() // Finaliza a definição para este campo
                .field("email", UserRegistrationDTO::email)
                .add(validation.isNotBlank("O e-mail não pode estar em branco."))
                .add(validation.email("O formato do e-mail é inválido."))
                .end()
                .field("password", UserRegistrationDTO::password)
                .add(validation.isNotBlank("A senha não pode estar em branco."))
                .add(validation.lengthMin(8, "A senha deve ter pelo menos 8 caracteres."))
                .end()
                .field("age", UserRegistrationDTO::age)
                .add(validation.min(18, "A idade mínima é 18 anos."))
                .end()
                .validate();
    }


}
