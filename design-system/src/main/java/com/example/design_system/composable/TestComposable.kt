package com.example.design_system.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun testComposable(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Row{
            Box(modifier = Modifier
                .weight(1f)
                .size(50.dp)
                .background(Color.Red)
                .padding(16.dp)
            ) {
                Text(text = "RED",
                    fontSize = 8.sp,
                )
            }
            Box(modifier = Modifier
                .padding(16.dp)
                .weight(2f)
                .size(50.dp)
                .background(Color.Green)
            ) {
                Text(text = "RED",
                    fontSize = 8.sp,
                )
            }
        }
        
    }
}

@ThemePreview
@Composable
fun showTestComposablePreview() {
    DesignTheme {
        testComposable()
    }
}
