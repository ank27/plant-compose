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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignSpaces
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

val designSpaces = DesignSpaces(
    spaceNone = 0.dp,
    space5XS = 1.dp,
    space4XS = 2.dp,
    space3XS = 4.dp,
    space2XS = 6.dp,
    spaceXS = 8.dp,
    spaceS = 12.dp,
    spaceM = 16.dp,
    spaceL = 24.dp,
    spaceXL = 32.dp,
    space2XL = 40.dp,
    space3XL = 48.dp,
    space4XL = 56.dp,
    space5XL = 64.dp,
    space6XL = 72.dp,
    space7XL = 96.dp
)

@ThemePreview
@Composable
fun PreviewSpace() {
    DesignTheme {
        val spaces = listOf(
            Pair("space-none", DesignTheme.spaces.spaceNone),
            Pair("space-5XS", DesignTheme.spaces.space5XS),
            Pair("space-4XS", DesignTheme.spaces.space4XS),
            Pair("space-3XS", DesignTheme.spaces.space3XS),
            Pair("space-2XS", DesignTheme.spaces.space2XS),
            Pair("space-XS", DesignTheme.spaces.spaceXS),
            Pair("space-S", DesignTheme.spaces.spaceS),
            Pair("space-M", DesignTheme.spaces.spaceM),
            Pair("space-L", DesignTheme.spaces.spaceL),
            Pair("space-XL", DesignTheme.spaces.spaceXL),
            Pair("space-2XL", DesignTheme.spaces.space2XL),
            Pair("space-3XL", DesignTheme.spaces.space3XL),
            Pair("space-4XL", DesignTheme.spaces.space4XL),
            Pair("space-5XL", DesignTheme.spaces.space5XL)
        )
        Column(
            modifier = Modifier
                .background(DesignTheme.colors.backgroundColors.bgBase)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    style = DesignTheme.typography.bodyRegular,
                    text = "Token", textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    style = DesignTheme.typography.bodyRegular,
                    text = "px",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    style = DesignTheme.typography.bodyRegular,
                    text = "Example",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
            spaces.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        style = DesignTheme.typography.bodyRegular,
                        text = it.first,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        style = DesignTheme.typography.bodyRegular,
                        text = it.second.value.toInt().toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Row() {
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(1.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(it.second)
                                    .height(41.dp)
                                    .background(DesignTheme.colors.fieldColors.primary)
                            )
                        }
                    }
                }
            }
        }
    }
}
