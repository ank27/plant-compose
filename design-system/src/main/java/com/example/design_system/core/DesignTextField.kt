package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

enum class TextFieldType {
    REGULAR,
    ERROR,
    WARNING,
    SUCCESS,
    DISABLED,
    DISPLAY,
}

@Composable
private fun TextFieldType.borderColor(isFocused: Boolean) = when (this) {
    TextFieldType.REGULAR -> if (isFocused)
        DesignTheme.colors.borderColors.primary else DesignTheme.colors.borderColors.tertiary
    TextFieldType.ERROR -> DesignTheme.colors.statusColors.error
    TextFieldType.WARNING -> DesignTheme.colors.statusColors.warning
    TextFieldType.SUCCESS -> DesignTheme.colors.statusColors.successBorder
    TextFieldType.DISABLED -> DesignTheme.colors.borderColors.tertiaryDisabled
    TextFieldType.DISPLAY -> Color.Transparent
}

@Composable
private fun TextFieldType.backgroundColor() = when (this) {
    TextFieldType.REGULAR -> DesignTheme.colors.fieldColors.secondary
    TextFieldType.ERROR -> DesignTheme.colors.fieldColors.secondary
    TextFieldType.WARNING -> DesignTheme.colors.fieldColors.secondary
    TextFieldType.SUCCESS -> DesignTheme.colors.fieldColors.secondary
    TextFieldType.DISABLED -> DesignTheme.colors.fieldColors.secondaryDisabled
    TextFieldType.DISPLAY -> Color.Transparent
}

@Composable
private fun TextFieldType.labelTextColor() = when (this) {
    TextFieldType.DISABLED -> DesignTheme.colors.textColors.disabled
    else -> DesignTheme.colors.textColors.secondary
}

@Composable
private fun TextFieldType.textColor() = when (this) {
    TextFieldType.DISABLED -> DesignTheme.colors.textColors.disabled
    else -> DesignTheme.colors.textColors.primary
}

@Composable
private fun TextFieldType.supportingTextColor() = when (this) {
    TextFieldType.REGULAR -> DesignTheme.colors.textColors.secondary
    TextFieldType.ERROR -> DesignTheme.colors.statusColors.errorText
    TextFieldType.WARNING -> DesignTheme.colors.statusColors.warningText
    TextFieldType.SUCCESS -> DesignTheme.colors.textColors.secondary
    TextFieldType.DISABLED -> DesignTheme.colors.textColors.disabled
    TextFieldType.DISPLAY -> Color.Transparent
}

@Composable
private fun TextFieldType.icon() = when (this) {
    TextFieldType.REGULAR -> null
    TextFieldType.ERROR -> DesignIcons.Status.Error.Filled to DesignTheme.colors.iconColors.danger
    TextFieldType.WARNING -> DesignIcons.Status.Warning.Filled to DesignTheme.colors.iconColors.warning
    TextFieldType.SUCCESS -> DesignIcons.Status.Success.Filled to DesignTheme.colors.iconColors.protected
    TextFieldType.DISABLED -> null
    TextFieldType.DISPLAY -> null
}

private enum class TextFieldInputType {
    REGULAR,
    PASSWORD,
}

/**
 * Number of lines in [TextFieldInput] with multiline capability.
 */
private const val MULTILINE_LINES = 3

/**
 * Minimum height of [TextFieldInput].
 */
private val MIN_HEIGHT = 36.dp

/**
 * Static text field (cannot be modified).
 *
 * @param inputType type of input [TextFieldInput]
 * @param isMultiline whether the text field should be display on multiple lines
 * @param label text above input
 * @param text input text
 * @param supportingText text below input
 */
@Composable
fun TextField(
    inputType: TextFieldType = TextFieldType.REGULAR,
    isMultiline: Boolean = false,
    label: String,
    text: String,
    supportingText: String? = null,
) {
    TextFieldInternal(
        editable = false,
        inputType = inputType,
        isMultiline = isMultiline,
        label = label,
        text = text,
        supportingText = supportingText,
        onTextChanged = {},
    )
}

/**
 * Dynamic text field (can be modified).
 *
 * @param inputType type of input [TextFieldInput]
 * @param isMultiline whether the text field should be display on multiple lines
 * @param label text above input
 * @param text input text
 * @param supportingText text below input
 * @param onTextChanged text change listener
 * @param keyboardOptions keyboard options
 */
@Composable
fun TextFieldInput(
    inputType: TextFieldType = TextFieldType.REGULAR,
    isMultiline: Boolean = false,
    label: String,
    text: String,
    supportingText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation? = null,
    onTextChanged: (String) -> Unit,
) {
    TextFieldInternal(
        editable = true,
        isMultiline = isMultiline,
        inputType = inputType,
        label = label,
        text = text,
        supportingText = supportingText,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        onTextChanged = onTextChanged,
    )
}

/**
 * Dynamic text field supports password input. It has icon for show/hide password value.
 *
 * @param inputType type of input [TextFieldInput]
 * @param label text above input
 * @param text input text
 * @param supportingText text below input
 * @param onTextChanged text change listener
 */
@Composable
fun TextFieldInputPassword(
    inputType: TextFieldType = TextFieldType.REGULAR,
    label: String,
    text: String,
    supportingText: String? = null,
    onTextChanged: (String) -> Unit,
) {
    TextFieldInternal(
        editable = true,
        inputType = inputType,
        textFieldInputType = TextFieldInputType.PASSWORD,
        label = label,
        text = text,
        supportingText = supportingText,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        onTextChanged = onTextChanged
    )
}

@Composable
private fun TextFieldInternal(
    editable: Boolean,
    isMultiline: Boolean = false,
    inputType: TextFieldType = TextFieldType.REGULAR,
    textFieldInputType: TextFieldInputType = TextFieldInputType.REGULAR,
    label: String,
    text: String,
    supportingText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation? = null,
    onTextChanged: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    var isTextEmpty by remember { mutableStateOf(text.isEmpty()) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val shape = RoundedCornerShape(DesignTheme.radii.radiusXS)
    val backgroundColor = inputType.backgroundColor()
    val borderColor = inputType.borderColor(isFocused)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = DesignTheme.typography.footnoteRegular,
            color = inputType.labelTextColor(),
            modifier = Modifier
                .padding(bottom = DesignTheme.spaces.space3XS)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MIN_HEIGHT)
                .background(color = backgroundColor, shape = shape)
                .border(width = 1.dp, color = borderColor, shape = shape)
                .padding(
                    horizontal = DesignTheme.spaces.spaceXS,
                    vertical = if (isMultiline) 9.dp else 0.dp,
                )
                .onFocusChanged { isFocused = it.isFocused },
            verticalAlignment = if (isMultiline) Alignment.Top else Alignment.CenterVertically
        ) {
            val textStyle =
                DesignTheme.typography.calloutEmphasized.copy(color = inputType.textColor())
            val transformation = visualTransformation
                ?: if (textFieldInputType == TextFieldInputType.PASSWORD && !isPasswordVisible)
                    PasswordVisualTransformation() else VisualTransformation.None
            BasicTextField(
                value = text,
                textStyle = textStyle,
                onValueChange = { value ->
                    onTextChanged(value)
                    isTextEmpty = value.isEmpty()
                },
                singleLine = !isMultiline,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = DesignTheme.spaces.spaceS),
                keyboardOptions = keyboardOptions,
                enabled = editable,
                visualTransformation = transformation,
                cursorBrush = SolidColor(DesignTheme.colors.borderColors.primary),
                minLines = if (isMultiline) MULTILINE_LINES else 1,
                maxLines = if (isMultiline) MULTILINE_LINES else 1,
            )
            val showPasswordIcon = textFieldInputType == TextFieldInputType.PASSWORD
            if (showPasswordIcon) {
                val icon = if (isPasswordVisible)
                    DesignIcons.Action.Hide.Filled else DesignIcons.Action.Show.Filled
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = DesignTheme.spaces.spaceS)
                        .clickable { isPasswordVisible = !isPasswordVisible },
                    tint = DesignTheme.colors.iconColors.standard,
                )
            }
            val showRemoveIcon =
                textFieldInputType == TextFieldInputType.REGULAR && editable && isFocused
            if (showRemoveIcon) {
                Icon(
                    painter = painterResource(id = DesignIcons.Navigation.Close.Filled),
                    contentDescription = null,
                    tint = if (isTextEmpty) DesignTheme.colors.iconColors.disabled
                    else DesignTheme.colors.iconColors.standard,
                    modifier = Modifier
                        .clickable {
                            onTextChanged("")
                            isTextEmpty = true
                        }
                )
            }
            inputType.icon()?.let {
                Icon(
                    painter = painterResource(id = it.first),
                    contentDescription = null,
                    tint = it.second,
                    modifier = Modifier
                )
            }
        }
        supportingText?.let {
            Text(
                text = it,
                style = DesignTheme.typography.footnoteRegular,
                color = inputType.supportingTextColor(),
                modifier = Modifier
                    .padding(top = DesignTheme.spaces.space3XS),
            )
        }
    }
}

@ThemePreview
@Composable
private fun TextFieldPreview() = DesignTheme {
    Column(
        modifier = Modifier.background(DesignTheme.colors.backgroundColors.bgBase),
        verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
    ) {
        TextFieldType.entries.forEach {
            TextField(
                inputType = it,
                label = "Input label",
                text = "$it",
                supportingText = "Supporting text",
            )
        }
    }
}

@ThemePreview
@Composable
private fun TextFieldInputPreview() = DesignTheme {
    Column(
        modifier = Modifier.background(DesignTheme.colors.backgroundColors.bgBase),
        verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
    ) {
        TextFieldType.entries.forEach {
            TextFieldInput(
                inputType = it,
                label = "Input label",
                text = "$it",
                supportingText = "Supporting text",
                onTextChanged = {},
            )
        }
    }
}

@ThemePreview
@Composable
private fun TextFieldInputMultilinePreview() = DesignTheme {
    Column(
        modifier = Modifier.background(DesignTheme.colors.backgroundColors.bgBase),
        verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
    ) {
        TextFieldType.entries.forEach {
            TextFieldInput(
                inputType = it,
                isMultiline = true,
                label = "Input label",
                text = "$it",
                supportingText = "Supporting text",
                onTextChanged = {},
            )
        }
    }
}

@ThemePreview
@Composable
private fun TextFieldInputPasswordPreview() = DesignTheme {
    Column(
        modifier = Modifier.background(DesignTheme.colors.backgroundColors.bgBase),
        verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
    ) {
        TextFieldType.entries.forEach {
            TextFieldInputPassword(
                inputType = it,
                label = "Input label",
                text = "$it",
                supportingText = "Supporting text",
                onTextChanged = {},
            )
        }
    }
}
