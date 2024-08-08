package com.example.taskmate.component.form

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * A composable function that represents a customizable text input field with support for validation errors.
 *
 * @param modifier Optional [Modifier] to be applied to the [TextField]. Defaults to [Modifier].
 * @param name The unique name for this field, used to retrieve and update its value in the form state.
 * @param errorTextStyle Optional [TextStyle] for styling error messages. Defaults to red text.
 * @param property An instance of [FieldProperty.Builder] used to configure various properties of the [TextField].
 */
@Composable
fun Field(
    modifier: Modifier = Modifier,
    name: String,
    errorTextStyle: TextStyle = TextStyle(color = Color.Red),
    property: FieldProperty.Builder = FieldProperty.Builder(),
) {
    // Obtain the current form state from the composition local
    val formState = LocalFormState.current

    // Retrieve the current value of the field from the form state
    val value: String = formState.values[name] as String

    // Build the field properties from the provided FieldProperty.Builder
    val fieldProperty = property.build()

    // Retrieve any validation errors associated with this field
    val error = formState.errors[name] ?: emptyList()

    // Define a composable function to display the error message with the specified text style
    val supportText: (@Composable () -> Unit) = {
        Text(error[0], style = errorTextStyle)
    }

    // Render the TextField with the specified properties and values
    TextField(
        value = value,
        onValueChange = {
            // Update the form state with the new value
            formState.setValue(name, it)
        },
        modifier = modifier,
        enabled = fieldProperty.enable,
        supportingText = if (error.isNotEmpty()) supportText else null,
        readOnly = fieldProperty.readOnly,
        isError = error.isNotEmpty(),
        singleLine = fieldProperty.singleLine,
        visualTransformation = fieldProperty.visualTransformation,
        maxLines = fieldProperty.maxLines,
        minLines = fieldProperty.minLines,
        keyboardOptions = fieldProperty.keyboardOptions,
        keyboardActions = fieldProperty.keyboardActions,
        textStyle = fieldProperty.textStyle,
        label = fieldProperty.label,
        placeholder = fieldProperty.placeholder,
        leadingIcon = fieldProperty.leadingIcon,
        trailingIcon = fieldProperty.trailingIcon,
        prefix = fieldProperty.prefix,
        suffix = fieldProperty.suffix,
        colors = fieldProperty.colors ?: TextFieldDefaults.colors(),
        shape = fieldProperty.shape ?: TextFieldDefaults.shape
    )
}
