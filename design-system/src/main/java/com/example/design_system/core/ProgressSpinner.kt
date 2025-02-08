package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.design_system.R
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

@Composable
fun ProgressSpinner(
    modifier: Modifier? = null,
    strokeWidth: Dp = dimensionResource(id = R.dimen.stroke_width)
) {
    CircularProgressIndicator(
        modifier = modifier ?: Modifier
            .padding(1.dp)
            .size(31.dp),
        color = DesignTheme.colors.iconColors.active,
        strokeWidth = strokeWidth
    )
}

@ThemePreview
@Composable
fun PreviewSpinner() {
    DesignTheme {
        Box(
            modifier = Modifier
                .background(
                    DesignTheme.colors.backgroundColors.bgBase
                )
                .padding(DesignTheme.spaces.spaceM)
        ) {
            ProgressSpinner()
        }
    }
}
