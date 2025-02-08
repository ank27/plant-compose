package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

@Composable
fun UcfRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    label: String? = null
) {
    Row(
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier
                        .clickable(
                            enabled = enabled,
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { onClick.invoke() }
                        )
                        .semantics { role = Role.RadioButton }
                } else Modifier
            )
            .padding(vertical = designSpaces.spaceXS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(designSpaces.spaceL)
                .clip(CircleShape)
                .border(
                    width = designSpaces.space4XS,
                    color = if (enabled) DesignTheme.colors.borderColors.secondary
                    else DesignTheme.colors.borderColors.secondaryDisabled,
                    shape = CircleShape
                )
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                enabled -> DesignTheme.colors.fieldColors.primary
                                else -> DesignTheme.colors.fieldColors.primaryDisabled
                            }
                        )
                )
            }
        }

        label?.let {
            Spacer(modifier = Modifier.width(designSpaces.spaceS))
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                color = when {
                    enabled -> MaterialTheme.colorScheme.onSurface
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

@ThemePreview
@Composable
fun PreviewRadioButton() {
    val combinations = listOf(
        Triple(true, true, "Enabled Selected"),
        Triple(true, false, "Enabled Unselected"),
        Triple(false, true, "Disabled Selected"),
        Triple(false, false, "Disabled Unselected")
    )
    DesignTheme {
        Column(
            modifier = Modifier
                .background(DesignTheme.colors.backgroundColors.bgBase)
                .padding(8.dp)
        ) {
            combinations.forEach {
                UcfRadioButton(
                    selected = it.second,
                    onClick = {
                    },
                    enabled = it.first,
                    label = it.third
                )
            }
        }
    }
}
