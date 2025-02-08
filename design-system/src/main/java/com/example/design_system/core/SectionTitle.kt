package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.design_system.R
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

@Composable
fun SectionTitle(
    title: String,
    isTitleBold: Boolean = false,
    leadingIcon: Int?,
    trailingComposable: @Composable (() -> Unit)? = null,
    showRipple: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = DesignTheme.spaces.spaceXS,
            alignment = Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = DesignTheme.spaces.spaceM,
                top = DesignTheme.spaces.space3XS,
                end = DesignTheme.spaces.spaceL,
                bottom = DesignTheme.spaces.space3XS
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = if (showRipple) ripple() else null,
                    ) {
                        onClick()
                    }
                } else {
                    Modifier
                }
            )
    ) {
        leadingIcon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = stringResource(id = R.string.image_desc_null),
                tint = DesignTheme.colors.iconColors.standard,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@ThemePreview
@Composable
fun PreviewSectionTitle() {
    val trailingComposableChevron: @Composable () -> Unit = {
        Icon(
            painter = painterResource(id = DesignIcons.Navigation.ChevronDown.Line),
            contentDescription = null
        )
    }

    val trailingComposableButton: @Composable () -> Unit = {
        DesignButtons.PrimarySmallButton(
            enabled = true,
            label = "label"
        ) {
        }
    }

    val dataSets = listOf(
        Triple(null, "List group name", null),
        Triple(null, "List group name", trailingComposableChevron),
        Triple(DesignIcons.Navigation.ChevronDown.Line, "List group name", trailingComposableChevron),
        Triple(DesignIcons.Navigation.ChevronDown.Line, "List group name", trailingComposableButton)
    )

    DesignTheme {
        Column(
            modifier = Modifier
                .background(DesignTheme.colors.backgroundColors.bgBase),
            verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM),
        ) {
            dataSets.forEach {
                SectionTitle(
                    title = it.second,
                    leadingIcon = it.first,
                    trailingComposable = it.third,
                )
            }
        }
    }
}
