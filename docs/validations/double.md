# Double Validations

**Type:** `Double`  
**Interface:** `DoubleValidations`

| Method | Description |
|--------|-------------|
| `eq(value, message)` | Equal to |
| `min(value, message)` | Greater than or equal to |
| `max(value, message)` | Less than or equal to |
| `between(min, max, message)` | Within inclusive range |
| `gt(threshold, message)` | Greater than |
| `lt(threshold, message)` | Less than |

> For positive, negative, or zero checks, use `gt(0.0, message)`, `lt(0.0, message)`, or `eq(0.0, message)`.

[← Back to index](../README.md)
