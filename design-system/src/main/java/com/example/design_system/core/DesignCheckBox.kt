package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

@Composable
fun DesignCheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    label: String? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = colors(
                checkedColor = DesignTheme.colors.fieldColors.primary,
                checkmarkColor = DesignTheme.colors.iconColors.onPrimaryField,
                disabledCheckedColor = DesignTheme.colors.fieldColors.primaryDisabled,
                uncheckedColor = DesignTheme.colors.borderColors.secondary,
                disabledUncheckedColor = DesignTheme.colors.borderColors.secondaryDisabled
            ),
            interactionSource = interactionSource
        )
        label?.let {
            Text(
                text = it,
                style = DesignTheme.typography.subHeadlineRegular,
                color = if (enabled) {
                    DesignTheme.colors.textColors.primary
                } else {
                    DesignTheme.colors.textColors.disabled
                }
            )
        }
    }
}

@ThemePreview
@Composable
fun PreviewCheckBox() {
    val combinations = listOf(
        Triple(true, true, "Enabled Checked"),
        Triple(true, false, "Enabled Unchecked"),
        Triple(false, true, "Disabled Checked"),
        Triple(false, false, "Disabled Unchecked")
    )
    DesignTheme {
        Column(
            modifier = Modifier
                .background(DesignTheme.colors.backgroundColors.bgBase)
                .padding(8.dp)
        ) {
            combinations.forEach {
                DesignCheckBox(
                    checked = it.second,
                    onCheckedChange = {
                    },
                    enabled = it.first,
                    label = it.third
                )
            }
        }
    }
}
