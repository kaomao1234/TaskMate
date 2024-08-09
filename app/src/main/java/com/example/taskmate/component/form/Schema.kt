package com.example.taskmate.component.form

/**
 * Represents a schema for validating form fields. Each field is associated with one or more validators.
 *
 * @param field A vararg parameter where each entry is a pair of field name and a function that creates a [Validator] for that field.
 */
class Schema(vararg field: Pair<String, (Any, Map<String, Any>) -> Validator>) {
    // Map of field names to their corresponding validators
    private val validators = mapOf(*field)

    /**
     * Validates the given field value using the schema's validators.
     *
     * @param field The name of the field to validate.
     * @param value The value of the field to be validated.
     * @param entireField A map containing all field values in the form, useful for cross-field validation.
     * @return A list of error messages if validation fails; otherwise, an empty list.
     */
    fun validate(field: String, value: String, entireField: Map<String, Any>): List<String> {
        // Get the validator for the field, if it exists
        val validator = validators[field]?.invoke(value, entireField) ?: return emptyList()
        // Return the list of validation errors
        return validator.errorList()
    }
}