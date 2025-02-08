package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignRadii
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

fun getDesignRadius(
    scalingFactor: Float = 1.0f
) = DesignRadii(
    radiusFull = 4096.dp,
    radiusL = (scalingFactor * 36).dp,
    radiusM = (scalingFactor * 24).dp,
    radiusS = (scalingFactor * 16).dp,
    radiusXS = (scalingFactor * 8).dp,
    radius2XS = (scalingFactor * 4).dp,
)

@ThemePreview
@Composable
fun Preview_radius() {
    var sliderPosition by remember { mutableStateOf(0f) }
    DesignTheme {
        val radiiData = listOf(
            Pair("radius-full", DesignTheme.radii.radiusFull),
            Pair("radius-l", DesignTheme.radii.radiusL),
            Pair("radius-m", DesignTheme.radii.radiusM),
            Pair("radius-s", DesignTheme.radii.radiusS),
            Pair("radius-xs", DesignTheme.radii.radiusXS),
            Pair("radius-2xs", DesignTheme.radii.radius2XS)
        )
        Column(
            modifier = Modifier.fillMaxSize()
                .background(DesignTheme.colors.backgroundColors.bgBase),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Scaling factor: $sliderPosition",
                style = DesignTheme.typography.headlineEmphasized
            )
            Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceXL))
            radiiData.forEach {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(
                            text = it.first,
                            style = DesignTheme.typography.subHeadlineRegular
                        )
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(it.second))
                                .background(DesignTheme.colors.fieldColors.primary)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceM))
            }

            Slider(
                colors = colors(
                    thumbColor = DesignTheme.colors.fieldColors.primary,
                    activeTrackColor = DesignTheme.colors.fieldColors.primary,
                    inactiveTrackColor = DesignTheme.colors.fieldColors.primaryDisabled
                ),
                value = sliderPosition,
                valueRange = 0f..2.0f,
                steps = 15,
                onValueChange = {
                    sliderPosition = it
                }
            )
        }
    }
}
