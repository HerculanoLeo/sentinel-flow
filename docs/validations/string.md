# String Validations

**Type:** `String`  
**Interface:** `StringValidations`

| Method | Description |
|--------|-------------|
| `isNotBlank(message)` | Must not be null, empty, or whitespace |
| `isBlank(message)` | Must be null, empty, or whitespace |
| `lengthEq(length, message)` | Exact length |
| `lengthMin(length, message)` | Minimum length |
| `lengthMax(length, message)` | Maximum length |
| `email(message)` | Valid email format |
| `onlyNumbers(message)` | Digits only |
| `url(message)` | Valid URL (scheme + host) |
| `https(message)` | Valid HTTPS URL |
| `fileExtension(extensions, message)` | File extension in the allowed set |
| `matches(pattern, message)` | Matches the given `Pattern` |
| `regex(pattern, message)` | Matches the given regex string |
| `uuid(message)` | Valid UUID |
| `alpha(message)` | Letters only (A–Z, a–z) |
| `alphaNumeric(message)` | Letters and digits only |
| `slug(message)` | Kebab-case slug (`my-page-title`) |
| `ipAddress(message)` | Valid IPv4 or IPv6 address |

[← Back to index](../README.md)
