package com.example.taskmate.component.form

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver

/**
 * Represents the state of a form, including field values and validation errors.
 *
 * @param initialValue A map of initial values for the form fields.
 */
class FormState(
    initialValue: Map<String, Any>
) {
    // Holds the current values of the form fields
    var values by mutableStateOf(initialValue)

    // Holds the validation errors for each field, initialized to empty lists
    var errors by mutableStateOf(initialValue.keys.associateWith { listOf<String>() })

    /**
     * Updates the value of a specific form field.
     *
     * @param field The name of the field to update.
     * @param value The new value for the field.
     */
    fun setValue(field: String, value: Any) {
        values = values.toMutableMap().apply { put(field, value) }
    }

    /**
     * Updates the validation errors for a specific field.
     *
     * @param field The name of the field to update errors for.
     * @param errors A list of error messages for the field.
     */
    fun setErrors(field: String, errors: List<String>) {
        this.errors = this.errors.toMutableMap().apply {
            this[field] = errors
        }
    }

    /**
     * Clears the validation errors for a specific field.
     *
     * @param field The name of the field to clear errors for.
     */
    fun clearErrors(field: String) {
        errors = errors.toMutableMap().apply {
            this[field] = emptyList()
        }
    }

    companion object {
        /**
         * A [Saver] implementation to handle saving and restoring [FormState] instances.
         *
         * Saves the form state as a map containing field values and errors.
         */
        val Saver: Saver<FormState, *> = mapSaver(
            save = { state ->
                mapOf(
                    "values" to state.values,
                    "errors" to state.errors
                )
            },
            restore = { saved ->
                FormState(
                    initialValue = saved["values"] as Map<String, Any>
                ).apply {
                    errors = saved["errors"] as Map<String, List<String>>
                }
            }
        )
    }
}
