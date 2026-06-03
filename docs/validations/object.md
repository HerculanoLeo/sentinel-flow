# Object Validations

**Type:** `<T>`  
**Interface:** `ObjectValidations`

| Method | Description |
|--------|-------------|
| `isNotNull(message)` | Value must not be null |
| `isNull(message)` | Value must be null |
| `isClass(message, clazz)` | Value must be an instance of the given class |
| `oneOf(allowed, message)` | Value must be contained in the set |
| `custom(predicate, message)` | Custom rule via `Predicate<T>` |

[← Back to index](../README.md)
