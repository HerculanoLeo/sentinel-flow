# SentinelFlow

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) <!-- Exemplo de badge, ajuste se necessário -->
<!-- Adicione outros badges relevantes aqui (versão, build status, etc.) -->

Uma biblioteca de validação **funcional** e **fluente** para objetos Java. `SentinelFlow` fornece uma maneira declarativa e concisa de definir e executar regras de validação complexas, utilizando o poder das interfaces funcionais e expressões lambda do Java moderno.

## Objetivo

O principal objetivo desta biblioteca é oferecer uma alternativa ou complemento às abordagens de validação tradicionais (como Bean Validation), especialmente útil para:

*   Regras de negócio complexas que são difíceis de expressar com anotações.
*   Validações condicionais ou que dependem de múltiplos campos.
*   Cenários onde a lógica de validação precisa ser construída dinamicamente.
*   Manter a lógica de validação desacoplada dos modelos (POJOs/DTOs).

Ela promove um estilo de código mais legível e expressivo para as validações através de sua API fluente.

## Principais Características

*   **Abordagem Funcional:** Define regras de validação como funções (lambdas), tornando a lógica clara e concisa.
*   **API Fluente:** Encadeie validações de forma natural e legível, melhorando a expressividade do código.
*   **Rico Conjunto de Validações:** Inclui validações comuns para Strings, Números, Datas, Objetos e formatos específicos brasileiros (CPF, CNPJ, CEP).
*   **Mensagens de Erro Customizáveis:** Fácil configuração de mensagens de erro significativas para cada regra de validação.
*   **Type-Safe:** As validações são aplicadas a tipos específicos, aproveitando a segurança de tipos do Java para evitar erros em tempo de execução.
*   **Extensível:** Permite a criação fácil de novas regras de validação customizadas, adaptando a biblioteca às necessidades específicas do seu projeto.
*   **Desacoplado:** Não requer que seus modelos (POJOs/DTOs) sejam anotados ou implementem interfaces específicas da biblioteca, mantendo seu domínio limpo.
*   **Testável:** A natureza funcional e a separação de responsabilidades facilitam a escrita de testes unitários tanto para as regras de validação quanto para a lógica que as utiliza.

## Instalação

Em projeto **Java 17** ou **superior**.

Adicione a dependência ao seu projeto.

**Maven:**

```xml
<dependency> 
    <groupId>com.herculanoleo</groupId> 
    <artifactId>java-functional-validation</artifactId> 
    <version>X.Y.Z</version> 
</dependency>
```

**Gradle:**
```groovy
implementation 'com.herculanoleo:java-functional-validation:X.Y.Z'
```
*(Nota: Substitua `X.Y.Z` pela versão mais recente da biblioteca.)*

## Como Usar (Aplicação Java Tradicional)

A validação é feita através da interface `Validate`, que é uma `@FunctionalInterface`. Você implementa a lógica de validação usando as fábricas `ValidatorFactory` e `ValidationFactory`. Em uma aplicação sem framework de injeção de dependência, você pode instanciar as implementações padrão diretamente.

**Exemplo Básico:**

Suponha que você tenha um DTO `UserRegistrationDTO`:

```java
public record UserRegistrationDTO(String name, String email, String password, int age) {}
```

Você pode definir e executar a validação da seguinte forma:

```java
import com.herculanoleo.jfv.validator.Validate; 
import com.herculanoleo.jfv.validator.ValidatorFactory; 
import com.herculanoleo.jfv.validations.ValidationFactory; 
import com.herculanoleo.jfv.exceptions.ValidatorException; 
// Importe as implementações padrão 
import com.herculanoleo.jfv.validator.impl.ValidatorFactoryImpl; 
import com.herculanoleo.jfv.validations.impl.ValidationFactoryImpl;

public class UserRegistrationValidator {
    
    // Instancia as fábricas (em apps maiores, considere um local centralizado para isso)
    private final ValidatorFactory validatorFactory = new ValidatorFactoryImpl();
    
    private final ValidationFactory validationFactory = new ValidationFactoryImpl();
    
    public void validateUser(UserRegistrationDTO dto) throws ValidatorException {
        // Cria a lógica de validação como uma lambda
        Validate validationLogic = (validator, validations) -> {
            validator.create(dto)
                .field("name", dto.name()) // Define o campo a ser validado
                    .add(validations.isNotBlank("O nome não pode estar em branco.")) // Adiciona regras
                    .add(validations.lengthMin(3, "O nome deve ter pelo menos 3 caracteres."))
                .end() // Finaliza a definição para este campo
                .field("email", dto.email())
                    .add(validations.isNotBlank("O e-mail não pode estar em branco."))
                    .add(validations.email("O formato do e-mail é inválido."))
                .end()
                .field("password", dto.password())
                    .add(validations.isNotBlank("A senha não pode estar em branco."))
                    .add(validations.lengthMin(8, "A senha deve ter pelo menos 8 caracteres."))
                .end()
                .field("age", dto.age())
                    .add(validations.min(18, "A idade mínima é 18 anos."))
                .end()
                .validate(); // Executa todas as validações definidas
        };
        // Executa a validação passando as instâncias das fábricas
        try {
            validationLogic.validate(this.validatorFactory, this.validationFactory);
            System.out.println("Validação do DTO bem-sucedida!");
        } catch (ValidatorException e) {
            System.err.println("Falha na validação do DTO:");
            e.getErrors().forEach(error ->
                System.err.println("- Campo: " + error.fieldName() + ", Erro: " + error.message())
            );
            // Re-lançar ou tratar a exceção conforme a necessidade da sua aplicação
            throw e;
        }
    }
    
    // Exemplo de como chamar a validação
    public static void main(String[] args) {
        // DTO com dados válidos
        UserRegistrationDTO validUser = new UserRegistrationDTO("John Doe", "john.doe@test.com", "password123", 30);
        // DTO com dados inválidos
        UserRegistrationDTO invalidUser = new UserRegistrationDTO("Jo", "invalid-email", "pass", 17);
        UserRegistrationValidator validator = new UserRegistrationValidator();
        System.out.println("--- Validando usuário válido ---");
        try {
            validator.validateUser(validUser);
        } catch (ValidatorException e) {
             // Não esperado neste caso
             System.err.println("Erro inesperado ao validar usuário válido: " + e.getMessage());
        }
        System.out.println("\n--- Validando usuário inválido ---");
        try {
            validator.validateUser(invalidUser);
        } catch (ValidatorException e) {
             // Erro esperado, as mensagens já foram impressas dentro do método validateUser
             System.out.println("Validação do usuário inválido falhou como esperado.");
        }
    }
}
```
**Principais Passos (Recapitulando):**

1.  **Instancie as Fábricas:** Crie instâncias de `ValidatorFactoryImpl` e `ValidationFactoryImpl`.
2.  **Defina a Lógica:** Crie uma instância de `Validate` (geralmente uma lambda) que recebe as fábricas como parâmetros.
3.  **Crie o Validator:** Dentro da lambda, use `validatorFactory.create(objetoParaValidar)`.
4.  **Defina Campos e Regras:** Use `.field("nomeDoCampo", valorDoCampo)` seguido por `.add(regraDeValidacao)` e finalize com `.end()`. A API é fluente, permitindo encadear múltiplos `.add()` para o mesmo campo.
5.  **Execute:** Chame `.validate()` no final da cadeia do `Validator`.
6.  **Chame e Trate a Exceção:** Execute a lambda `validationLogic.validate(instanciaValidatorFactory, instanciaValidationFactory)` e prepare-se para capturar a `ValidatorException` em caso de falha.

Este exemplo mostra o uso básico em um cenário sem frameworks complexos, focando na instanciação direta e na execução da lógica de validação.

## Integração com Spring Boot

`SentinelFlow` pode ser facilmente integrada em aplicações Spring Boot. As fábricas `ValidatorFactory` e `ValidationFactory` podem ser declaradas como Beans gerenciados pelo Spring, facilitando a injeção onde forem necessárias.

**1. Configuração dos Beans:**

Crie uma classe de configuração para registrar as implementações das fábricas no contexto do Spring.

```java
import com.herculanoleo.jfv.validator.ValidatorFactory; import com.herculanoleo.jfv.validator.impl.ValidatorFactoryImpl; import com.herculanoleo.jfv.validations.ValidationFactory; import com.herculanoleo.jfv.validations.impl.ValidationFactoryImpl; import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;

@Configuration 
public class ValidationConfig {
    
    @Bean
    public ValidationFactory validationFactory() {
        // Retorna a implementação padrão da fábrica de regras
        return new ValidationFactoryImpl();
    }
    
    @Bean
    public ValidatorFactory validatorFactory() {
        // Retorna a implementação padrão da fábrica de validadores
        // Nota: Se ValidatorFactoryImpl precisar de outras dependências no futuro,
        // o Spring pode injetá-las automaticamente se também forem beans.
        return new ValidatorFactoryImpl();
    }
}
```
**2. Uso em um Componente (Ex: Service):**

Agora você pode injetar as fábricas em seus serviços ou outros componentes Spring e usar a interface `Validate` para definir e executar a lógica de validação.

```java 
import com.herculanoleo.jfv.validator.Validate; 
import com.herculanoleo.jfv.validator.ValidatorFactory; 
import com.herculanoleo.jfv.validations.ValidationFactory; 
import com.herculanoleo.jfv.exceptions.ValidatorException; 
import org.springframework.stereotype.Service;
// Supondo que UserRegistrationDTO exista // 
import com.yourpackage.dto.UserRegistrationDTO;

@Service 
public class UserService {
    
    private final ValidatorFactory validatorFactory;
    
    private final ValidationFactory validationFactory;
    
    // Injeção de dependência via construtor (recomendado)
    public UserService(ValidatorFactory validatorFactory, ValidationFactory validationFactory) {
        this.validatorFactory = validatorFactory;
        this.validationFactory = validationFactory;
    }
    
    public void registerUser(UserRegistrationDTO dto) {
        // Define a lógica de validação específica para este método/caso de uso
        // A lambda recebe as fábricas que serão usadas internamente.
        Validate userValidationLogic = (validator, validations) -> {
            validator.create(dto) // Usa a fábrica injetada
                .field("name", dto.name())
                    .add(validations.isNotBlank("O nome é obrigatório.")) // Usa a fábrica injetada
                    .add(validations.lengthMin(3, "Nome muito curto."))
                .end()
                .field("email", dto.email())
                    .add(validations.isNotBlank("O e-mail é obrigatório."))
                    .add(validations.email("Formato de e-mail inválido."))
                .end()
                .field("age", dto.age())
                    .add(validations.min(18, "Deve ser maior de idade."))
                .end()
                // ... outras validações complexas ou de negócio
                .validate(); // Executa a validação
        };
        try {
            // Executa a validação usando os beans injetados
            userValidationLogic.validate(this.validatorFactory, this.validationFactory);
            // Se chegou aqui, a validação passou. Prossiga com a lógica de negócio.
            System.out.println("Usuário válido, registrando...");
            // ... salvar usuário no banco, chamar outros serviços, etc.
        } catch (ValidatorException e) {
            // Lógica para tratar falhas de validação
            // Ex: Logar os erros, lançar uma exceção específica da aplicação (ex: BadRequestException)
            // que pode ser tratada por um @ControllerAdvice globalmente para retornar um 400 Bad Request.
            System.err.println("Erro de validação ao registrar usuário: " + e.getErrors());
            // É uma boa prática encapsular a ValidatorException em uma exceção da sua camada de aplicação.
            throw new IllegalArgumentException("Dados de registro inválidos: " + e.getErrors());
            // Ou uma exceção customizada: throw new BusinessValidationException(e.getErrors());
        }
    }
}
```

**Principais Vantagens com Spring:**

*   **Injeção de Dependência:** O Spring gerencia o ciclo de vida das fábricas.
*   **Facilidade de Teste:** Você pode facilmente mockar `ValidatorFactory` e `ValidationFactory` nos testes unitários dos seus serviços.
*   **Consistência:** Garante que a mesma instância configurada das fábricas seja usada em toda a aplicação.

Este exemplo demonstra como a biblioteca se integra perfeitamente ao ecossistema Spring Boot, permitindo que você defina validações funcionais de forma limpa e desacoplada dentro dos seus componentes gerenciados.
