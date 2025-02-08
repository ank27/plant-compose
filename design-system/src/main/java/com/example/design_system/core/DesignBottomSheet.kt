package com.example.design_system.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignBottomSheet(
    title: String,
    testTag: String,
    containerColor: Color,
    trailingComposable: @Composable (() -> Unit)? = null,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable (() -> Unit),
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = containerColor,
        contentWindowInsets = {
            WindowInsets(
                0.dp,
                WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                0.dp,
                WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
            )
        }
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            title = {
                Text(
                    text = title,
                    style = DesignTheme.typography.headlineEmphasized,
                    modifier = Modifier.testTag(
                        testTag.plus("_title"),
                    )
                )
            },
            actions = {
                trailingComposable?.invoke()
            },
            windowInsets = WindowInsets(0, 0, 0, 0)
        )
        Box(
            modifier = Modifier.padding(
                start = DesignTheme.spaces.spaceL,
                end = DesignTheme.spaces.spaceL,
                bottom = DesignTheme.spaces.spaceL
            )
        ) {
            content.invoke()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreview
@Composable
private fun PreviewBottomSheet() {
    DesignTheme {
        DesignBottomSheet(
            title = "view name",
            testTag = "",
            onDismissRequest = { },
            containerColor = DesignTheme.colors.backgroundColors.bgBase
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                repeat(50) {
                    Text(
                        color = DesignTheme.colors.textColors.primary,
                        text = "This is text line {$it}"
                    )
                }
            }
        }
    }
}
