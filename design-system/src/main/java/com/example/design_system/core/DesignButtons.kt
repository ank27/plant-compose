package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview
import com.example.design_system.core.DesignButtons.PrimaryLargeButton
import com.example.design_system.core.DesignButtons.PrimaryMediumButton
import com.example.design_system.core.DesignButtons.PrimarySmallButton
import com.example.design_system.core.DesignButtons.SecondaryLargeButton
import com.example.design_system.core.DesignButtons.SecondaryMediumButton
import com.example.design_system.core.DesignButtons.SecondarySmallButton
import com.example.design_system.core.DesignButtons.TertiaryLargeButton
import com.example.design_system.core.DesignButtons.TertiaryMediumButton
import com.example.design_system.core.DesignButtons.TertiarySmallButton

object DesignButtons {
    private object ButtonPaddings {
        val Gap = designSpaces.space4XS
        val GapSmall = designSpaces.space4XS
        val Vertical = designSpaces.spaceXS
        val VerticalSmall = designSpaces.space3XS
        val VerticalLarge = designSpaces.spaceS
        val Horizontal = designSpaces.spaceM
        val HorizontalSmall = designSpaces.spaceS
    }

    private object IconButtonPaddings {
        val Default = designSpaces.space2XS
        val Small = designSpaces.spaceNone
        val Large = designSpaces.spaceS
    }

    private data class ButtonColor(
        val background: Color,
        val borderColor: Color? = null,
        val content: Color
    )

    private data class ViewPadding(
        val start: Dp,
        val top: Dp,
        val end: Dp,
        val bottom: Dp
    )

    enum class ButtonType {
        PRIMARY,
        SECONDARY,
        TERTIARY,
    }

    private enum class ButtonSize {
        SMALL,
        MEDIUM,
        LARGE
    }
    @Composable
    fun PrimaryButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        text: String
    ) {

        androidx.compose.material3.Button(
            onClick = {
                onClick()
            },
            enabled = enabled,
            modifier = modifier.heightIn(min = 48.dp)
        ) {
            Text(text = text)
        }
    }

    @Composable
    private fun getButtonPadding(
        buttonSize: ButtonSize,
        hasOnlyIconBody: Boolean,
        hasLeadingIcon: Boolean,
        hasTrailingIcon: Boolean
    ): ViewPadding {
        val getOffset = { hasIcon: Boolean ->
            val isSmallButton = buttonSize == ButtonSize.SMALL
            when {
                hasOnlyIconBody -> 0.dp
                hasIcon && isSmallButton -> 4.dp
                hasIcon -> 8.dp
                else -> 0.dp
            }
        }

        val startOffset = getOffset(hasLeadingIcon)
        val endOffset = getOffset(hasTrailingIcon)
        return when (buttonSize) {
            ButtonSize.LARGE -> {
                if (hasOnlyIconBody) {
                    ViewPadding(
                        start = IconButtonPaddings.Large,
                        end = IconButtonPaddings.Large,
                        top = IconButtonPaddings.Large,
                        bottom = IconButtonPaddings.Large
                    )
                } else {
                    ViewPadding(
                        start = ButtonPaddings.Horizontal,
                        end = ButtonPaddings.Horizontal,
                        top = ButtonPaddings.VerticalLarge,
                        bottom = ButtonPaddings.VerticalLarge
                    )
                }
            }

            ButtonSize.MEDIUM -> {
                if (hasOnlyIconBody) {
                    ViewPadding(
                        start = IconButtonPaddings.Default,
                        end = IconButtonPaddings.Default,
                        top = IconButtonPaddings.Default,
                        bottom = IconButtonPaddings.Default
                    )
                } else {
                    ViewPadding(
                        start = ButtonPaddings.Horizontal,
                        end = ButtonPaddings.Horizontal,
                        top = ButtonPaddings.Vertical,
                        bottom = ButtonPaddings.Vertical
                    )
                }
            }

            ButtonSize.SMALL -> {
                if (hasOnlyIconBody) {
                    ViewPadding(
                        start = IconButtonPaddings.Small,
                        end = IconButtonPaddings.Small,
                        top = IconButtonPaddings.Small,
                        bottom = IconButtonPaddings.Small
                    )
                } else {
                    ViewPadding(
                        start = ButtonPaddings.HorizontalSmall,
                        end = ButtonPaddings.HorizontalSmall,
                        top = ButtonPaddings.VerticalSmall,
                        bottom = ButtonPaddings.VerticalSmall
                    )
                }
            }
        }.let {
            it.copy(
                start = it.start - startOffset,
                end = it.end - endOffset
            )
        }
    }

    @Composable
    private fun CreateButtonColor(
        isPressed: Boolean,
        enabled: Boolean,
        buttonType: ButtonType,
        buttonColor: MutableState<ButtonColor>
    ) {
        buttonColor.value = when (buttonType) {
            ButtonType.PRIMARY -> {
                when {
                    enabled && isPressed -> ButtonColor(
                        background = DesignTheme.colors.fieldColors.primaryPressed,
                        content = DesignTheme.colors.textColors.onPrimaryField
                    )

                    enabled -> ButtonColor(
                        background = DesignTheme.colors.fieldColors.primary,
                        content = DesignTheme.colors.textColors.onPrimaryField
                    )

                    else -> ButtonColor(
                        background = DesignTheme.colors.fieldColors.primaryDisabled,
                        content = DesignTheme.colors.textColors.primary
                    )
                }
            }

            ButtonType.SECONDARY -> {
                when {
                    enabled && isPressed -> ButtonColor(
                        background = DesignTheme.colors.fieldColors.secondaryPressed,
                        content = DesignTheme.colors.textColors.onSecondaryField,
                        borderColor = DesignTheme.colors.borderColors.primaryPressed
                    )

                    enabled -> ButtonColor(
                        background = DesignTheme.colors.fieldColors.secondary,
                        content = DesignTheme.colors.textColors.onSecondaryField,
                        borderColor = DesignTheme.colors.borderColors.primary
                    )

                    else -> ButtonColor(
                        background = DesignTheme.colors.fieldColors.secondaryDisabled,
                        content = DesignTheme.colors.textColors.onSecondaryFieldDisabled,
                        borderColor = DesignTheme.colors.borderColors.primaryDisabled
                    )
                }
            }

            ButtonType.TERTIARY -> {
                when {
                    enabled && isPressed -> {
                        ButtonColor(
                            background = DesignTheme.colors.fieldColors.secondaryPressed,
                            content = DesignTheme.colors.textColors.primary
                        )
                    }

                    enabled -> {
                        ButtonColor(
                            background = Color.Transparent,
                            content = DesignTheme.colors.textColors.primary
                        )
                    }

                    else -> {
                        ButtonColor(
                            background = Color.Transparent,
                            content = DesignTheme.colors.textColors.disabled
                        )
                    }
                }
            }
        }
    }


    @Composable
    private fun DesignButtonBody(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        contentColor: Color,
        buttonSize: ButtonSize,
    ) {
        val isSmallButton = buttonSize == ButtonSize.SMALL
        val iconSize: Dp
        val textStyle: TextStyle
        val spacedBy: Dp

        if (isSmallButton) {
            iconSize = 20.dp
            textStyle = DesignTheme.typography.subHeadlineRegular
            spacedBy = ButtonPaddings.GapSmall
        } else {
            iconSize = 24.dp
            textStyle = DesignTheme.typography.bodyRegular
            spacedBy = ButtonPaddings.Gap
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                spacedBy,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIconId?.let {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    tint = contentColor,
                    painter = painterResource(id = it),
                    contentDescription = null,
                )
            }
            label?.let {
                Text(
                    text = it,
                    style = textStyle,
                    color = contentColor,
                )
            }
            trailingIconId?.let {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    tint = contentColor,
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
        }
    }

    @Composable
    private fun CreateButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        modifier: Modifier,
        contentColor: Color,
        buttonSize: ButtonSize,
    ) {
        Box(
            modifier = modifier.semantics { role = Role.Button },
            contentAlignment = Alignment.Center
        ) {
            DesignButtonBody(
                contentColor = contentColor,
                label = label,
                trailingIconId = trailingIconId,
                leadingIconId = leadingIconId,
                buttonSize = buttonSize,
            )
        }
    }

    @Composable
    private fun Button(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        fillMaxWidth: Boolean = false,
        enabled: Boolean,
        buttonType: ButtonType,
        buttonSize: ButtonSize,
        onClick: () -> Unit,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val buttonColor = remember {
            mutableStateOf(
                ButtonColor(
                    background = Color.Blue,
                    borderColor = Color.Blue,
                    content = Color.Blue
                )
            )
        }

        val hasExactlyOneIcon = (leadingIconId == null) xor (trailingIconId == null)
        val hasOnlyIconBody = label.isNullOrEmpty() && hasExactlyOneIcon

        val viewPadding = getButtonPadding(
            buttonSize = buttonSize,
            hasOnlyIconBody = hasOnlyIconBody,
            hasLeadingIcon = leadingIconId != null,
            hasTrailingIcon = trailingIconId != null
        )

        CreateButtonColor(
            isPressed = isPressed,
            enabled = enabled,
            buttonType = buttonType,
            buttonColor = buttonColor
        )

        val buttonCornerRadius = if (buttonSize == ButtonSize.SMALL) {
            DesignTheme.radii.radiusXS
        } else {
            DesignTheme.radii.radiusM
        }

        val buttonModifier = Modifier
            .then(
                buttonColor.value.borderColor?.let {
                    Modifier.border(
                        width = 1.dp,
                        color = it,
                        shape = RoundedCornerShape(size = buttonCornerRadius)
                    )
                } ?: Modifier
            )
            .then(
                if (fillMaxWidth && !hasOnlyIconBody) {
                    Modifier.fillMaxWidth()
                } else Modifier
            )
            .background(
                color = buttonColor.value.background,
                shape = RoundedCornerShape(
                    size = buttonCornerRadius
                )
            )
            .padding(
                start = viewPadding.start,
                end = viewPadding.end,
                top = viewPadding.top,
                bottom = viewPadding.bottom
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled
            ) {
                onClick()
            }

        CreateButton(
            modifier = buttonModifier,
            contentColor = buttonColor.value.content,
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            buttonSize = buttonSize,
        )
    }

    /**
     * Creates button of primary type and large size
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param fillMaxWidth whether the button should occupy whole width
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun PrimaryLargeButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        fillMaxWidth: Boolean = false,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            fillMaxWidth = fillMaxWidth,
            enabled = enabled,
            buttonType = ButtonType.PRIMARY,
            buttonSize = ButtonSize.LARGE,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of primary type and large medium
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun PrimaryMediumButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        enabled: Boolean = true,
        fillMaxWidth: Boolean = false,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            enabled = enabled,
            fillMaxWidth = fillMaxWidth,
            buttonType = ButtonType.PRIMARY,
            buttonSize = ButtonSize.MEDIUM,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of primary type and large small
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun PrimarySmallButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        enabled: Boolean = true,
        fillMaxWidth: Boolean = false,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            enabled = enabled,
            buttonType = ButtonType.PRIMARY,
            buttonSize = ButtonSize.SMALL,
            fillMaxWidth = fillMaxWidth,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of secondary type and large size
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param fillMaxWidth whether the button should occupy whole width
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun SecondaryLargeButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        fillMaxWidth: Boolean = false,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            fillMaxWidth = fillMaxWidth,
            enabled = enabled,
            buttonType = ButtonType.SECONDARY,
            buttonSize = ButtonSize.LARGE,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of secondary type and medium size
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun SecondaryMediumButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        enabled: Boolean = true,
        fillMaxWidth: Boolean = false,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            enabled = enabled,
            buttonType = ButtonType.SECONDARY,
            buttonSize = ButtonSize.MEDIUM,
            fillMaxWidth = fillMaxWidth,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of secondary type and small size
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun SecondarySmallButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        enabled: Boolean = true,
        fillMaxWidth: Boolean = false,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            enabled = enabled,
            fillMaxWidth = fillMaxWidth,
            buttonType = ButtonType.SECONDARY,
            buttonSize = ButtonSize.SMALL,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of tertiary type and large size
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param fillMaxWidth whether the button should occupy whole width
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun TertiaryLargeButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        fillMaxWidth: Boolean = false,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            fillMaxWidth = fillMaxWidth,
            enabled = enabled,
            buttonType = ButtonType.TERTIARY,
            buttonSize = ButtonSize.LARGE,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of tertiary type and medium size
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun TertiaryMediumButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            enabled = enabled,
            buttonType = ButtonType.TERTIARY,
            buttonSize = ButtonSize.MEDIUM,
        ) {
            onClick()
        }
    }

    /**
     * Creates button of tertiary type and small size
     *
     * @param leadingIconId Icon before the label
     * @param trailingIconId Icon after the label
     * @param label text of the button
     * @param enabled state of the button
     * @param onClick click action listener
     */
    @Composable
    fun TertiarySmallButton(
        leadingIconId: Int? = null,
        trailingIconId: Int? = null,
        label: String? = null,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) {
        Button(
            leadingIconId = leadingIconId,
            trailingIconId = trailingIconId,
            label = label,
            enabled = enabled,
            buttonType = ButtonType.TERTIARY,
            buttonSize = ButtonSize.SMALL,
        ) {
            onClick()
        }
    }
}

@ThemePreview
@Composable
fun PreviewPrimaryButtons() {
    val buttonDataset = getPreviewButtonDataset()
    val enabledStates = listOf(true, false)

    PreviewButtons(buttonType = DesignButtons.ButtonType.PRIMARY) {
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    PrimaryLargeButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    PrimaryMediumButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    PrimarySmallButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
    }
}

@ThemePreview
@Composable
fun PreviewSecondaryButtons() {
    val buttonDataset = getPreviewButtonDataset()
    val enabledStates = listOf(true, false)

    PreviewButtons(buttonType = DesignButtons.ButtonType.SECONDARY) {
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    SecondaryLargeButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    SecondaryMediumButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    SecondarySmallButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
    }
}

@ThemePreview
@Composable
fun PreviewTertiaryButtons() {
    val buttonDataset = getPreviewButtonDataset()
    val enabledStates = listOf(true, false)

    PreviewButtons(buttonType = DesignButtons.ButtonType.TERTIARY) {
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    TertiaryLargeButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    TertiaryMediumButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
        buttonDataset.forEach { buttonData ->
            previewButtonsRow {
                enabledStates.forEach { buttonState ->
                    TertiarySmallButton(
                        enabled = buttonState,
                        leadingIconId = buttonData.leadingIcon,
                        trailingIconId = buttonData.trailingIconId,
                        label = buttonData.label
                    ) {
                    }
                }
            }
        }
    }
}

private data class PreviewButtonBody(
    val trailingIconId: Int? = null,
    val label: String? = null,
    val leadingIcon: Int? = null
)

private fun getPreviewButtonDataset(): List<PreviewButtonBody> {
    val label = "label"
    val leadingIconId = DesignIcons.Status.Info.Line
    val trailingIconId = DesignIcons.Navigation.ChevronDown.Line

    return listOf(
        PreviewButtonBody(label = label),
        PreviewButtonBody(leadingIcon = leadingIconId, label = label),
        PreviewButtonBody(
            leadingIcon = leadingIconId,
            label = label,
            trailingIconId = trailingIconId
        ),
        PreviewButtonBody(label = label, trailingIconId = trailingIconId),
        PreviewButtonBody(trailingIconId = trailingIconId)
    )
}

@Composable
private fun previewButtonsRow(
    buttons: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        buttons()
    }
}

@Composable
private fun PreviewButtons(
    buttonType: DesignButtons.ButtonType,
    buttons: @Composable ColumnScope.() -> Unit,
) {
    DesignTheme {
        Column(
            modifier = Modifier
                .background(DesignTheme.colors.backgroundColors.bgBase)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = DesignTheme.typography.title2Regular,
                text = when (buttonType) {
                    DesignButtons.ButtonType.PRIMARY -> "Primary Buttons"
                    DesignButtons.ButtonType.SECONDARY -> "Secondary Buttons"
                    DesignButtons.ButtonType.TERTIARY -> "Tertiary Buttons"
                }
            )
            buttons()
        }
    }
}

@ThemePreview
@Composable
fun PreviewFullWidthButton() {
    DesignTheme {
        Column(
            modifier = Modifier
                .background(DesignTheme.colors.backgroundColors.bgBase)
                .padding(
                    top = 24.dp,
                    bottom = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
        ) {
            PrimaryLargeButton(
                enabled = true,
                label = "full width primary",
                fillMaxWidth = true,
            ) {
            }
            SecondaryLargeButton(
                enabled = true,
                label = "full width secondary",
                fillMaxWidth = true,
            ) {
            }
        }
    }
}

