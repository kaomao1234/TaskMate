package com.example.taskmate.component.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment

/**
 * A composition local providing the [FormState] instance for the form.
 * Throws an error if no [FormState] is provided.
 */
val LocalFormState = compositionLocalOf<FormState> { error("No FormState provided") }

/**
 * Composable function representing a form container that manages its state and validation.
 *
 * @param modifier Optional [Modifier] to be applied to the [Column].
 * @param vertical Arrangement for vertical positioning of children in the form. Defaults to [Arrangement.Top].
 * @param horizontal Alignment for horizontal positioning of children in the form. Defaults to [Alignment.Start].
 * @param initialValue A map of initial values for the form fields.
 * @param schema An instance of [Schema] used for validating form field values.
 * @param onSubmit A lambda function to be called when the form is successfully submitted, with the form values as the parameter.
 * @param children A composable lambda that provides a handleSubmit function for submitting the form.
 */
@Composable
fun Form(
    modifier: Modifier,
    vertical: Arrangement.Vertical = Arrangement.Top,
    horizontal: Alignment.Horizontal = Alignment.Start,
    initialValue: Map<String, Any>,
    schema: Schema,
    onSubmit: (value: Map<String, Any>) -> Unit,
    children: @Composable (handleSubmit: () -> Unit) -> Unit,
) {
    // Remember and save the form state instance with its saver
    val formState = rememberSaveable(saver = FormState.Saver) {
        FormState(initialValue)
    }

    /**
     * Handles form submission by validating all field values and invoking the onSubmit callback if all values are valid.
     */
    fun handleSubmit() {
        // Validate all form values using the provided schema
        val allValid = formState.values.map { (key, value) ->
            // Validate each field using the schema, passing the entire form's values
            val errors = schema.validate(key, value as String,formState.values)

            if (errors.isNotEmpty()) {
                formState.setErrors(key, errors)
                false
            } else {
                formState.clearErrors(key)
                true
            }
        }

        // Call onSubmit if all fields are valid
        if (allValid.all { it }) {
            onSubmit(formState.values)
        }
    }

    // Provide the form state to the composition local
    CompositionLocalProvider(LocalFormState provides formState) {
        // Render the form layout using a Column
        Column(
            modifier = modifier,
            verticalArrangement = vertical,
            horizontalAlignment = horizontal
        ) {
            // Render the form's children composables, providing the handleSubmit function
            children(::handleSubmit)
        }
    }
}
