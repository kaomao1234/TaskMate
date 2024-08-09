package com.example.taskmate.component.form

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

class FormFieldProperty private constructor(
    val enable: Boolean,
    val readOnly: Boolean,
    val isError: Boolean,
    val singleLine: Boolean,
    val visualTransformation: VisualTransformation,
    val maxLines: Int,
    val minLines: Int,
    val keyboardOptions: KeyboardOptions,
    val keyboardActions: KeyboardActions,
    val textStyle: TextStyle,
    val label: (@Composable (() -> Unit))?,
    val placeholder: (@Composable (() -> Unit))?,
    val leadingIcon: (@Composable (() -> Unit))?,
    val trailingIcon: (@Composable (() -> Unit))?,
    val prefix: (@Composable (() -> Unit))?,
    val suffix: (@Composable (() -> Unit))?,
    val shape: Shape?,
    val colors: TextFieldColors?
) {
    // Builder class for FieldProperty
    class Builder : Cloneable {
        private var enable: Boolean = true
        private var readOnly: Boolean = false
        private var isError: Boolean = false
        private var singleLine: Boolean = false
        private var visualTransformation: VisualTransformation = VisualTransformation.None
        private var maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE
        private var minLines: Int = 1
        private var keyboardOptions: KeyboardOptions = KeyboardOptions.Default
        private var keyboardActions: KeyboardActions = KeyboardActions.Default
        private var textStyle: TextStyle = TextStyle.Default
        private var label: (@Composable (() -> Unit))? = null
        private var placeholder: (@Composable (() -> Unit))? = null
        private var leadingIcon: (@Composable (() -> Unit))? = null
        private var trailingIcon: (@Composable (() -> Unit))? = null
        private var prefix: (@Composable (() -> Unit))? = null
        private var suffix: (@Composable (() -> Unit))? = null
        private var shape: Shape? = null
        private var colors: TextFieldColors? = null


        fun setEnable(value: Boolean) = apply { this.enable = value }
        fun setReadOnly(value: Boolean) = apply { this.readOnly = value }
        fun setIsError(value: Boolean) = apply { this.isError = value }
        fun setSingleLine(value: Boolean) = apply { this.singleLine = value }
        fun setVisualTransformation(value: VisualTransformation) =
            apply { this.visualTransformation = value }

        fun setMaxLines(value: Int) = apply { this.maxLines = value }
        fun setMinLines(value: Int) = apply { this.minLines = value }
        fun setKeyboardOptions(value: KeyboardOptions) = apply { this.keyboardOptions = value }
        fun setKeyboardActions(value: KeyboardActions) = apply { this.keyboardActions = value }
        fun setTextStyle(value: TextStyle) = apply { this.textStyle = value }
        fun setLabel(value: @Composable () -> Unit) = apply { this.label = value }
        fun setPlaceholder(value: @Composable () -> Unit) = apply { this.placeholder = value }
        fun setLeadingIcon(value: @Composable () -> Unit) = apply { this.leadingIcon = value }
        fun setTrailingIcon(value: @Composable () -> Unit) = apply { this.trailingIcon = value }
        fun setPrefix(value: @Composable () -> Unit) = apply { this.prefix = value }
        fun setSuffix(value: @Composable () -> Unit) = apply { this.suffix = value }
        fun setShape(value: Shape) = apply {
            this.shape = value
        }

        fun setColors(value: TextFieldColors) = apply { this.colors = value }
        fun copy(): Builder {
            return clone() as Builder
        }

        fun build(): FormFieldProperty {
            return FormFieldProperty(
                enable,
                readOnly,
                isError,
                singleLine,
                visualTransformation,
                maxLines,
                minLines,
                keyboardOptions,
                keyboardActions,
                textStyle,
                label,
                placeholder,
                leadingIcon,
                trailingIcon,
                prefix,
                suffix,
                shape,
                colors
            )
        }
    }
}
