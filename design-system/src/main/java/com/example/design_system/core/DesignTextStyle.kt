package com.example.design_system.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.DesignTypography
import com.example.design_system.composable.ThemePreview

fun getTypography(textColor: Color) =
    DesignTypography(
        largeTitleRegular = TextStyle(
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight(400),
            color = textColor
        ),
        title1Regular = TextStyle(
            fontSize = 28.sp,
            lineHeight = 36.sp,
            fontWeight = FontWeight(400),
            color = textColor
        ),
        title2Regular = TextStyle(
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight(400),
            color = textColor
        ),
        title3Regular = TextStyle(
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
            color = textColor
        ),
        headlineRegular = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.15.sp,
            color = textColor
        ),
        bodyRegular = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.5.sp,
            color = textColor
        ),
        calloutRegular = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.25.sp,
            color = textColor
        ),
        subHeadlineRegular = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.15.sp,
            color = textColor
        ),
        footnoteRegular = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.5.sp,
            color = textColor
        ),
        captionRegular = TextStyle(
            fontSize = 11.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.5.sp,
            color = textColor
        )
    )

@ThemePreview
@Composable
private fun previewFonts() {
    DesignTheme {
        val typographies = listOf(
            listOf(
                Pair("Android Large Title - Regular", DesignTheme.typography.largeTitleRegular),
                Pair("Android Large Title - Emphasized", DesignTheme.typography.largeTitleEmphasized)
            ),
            listOf(
                Pair("Android Title 1 - Regular", DesignTheme.typography.title1Regular),
                Pair("Android Title 1 - Emphasized", DesignTheme.typography.title1Emphasized)
            ),
            listOf(
                Pair("Android Title 2 - Regular", DesignTheme.typography.title2Regular),
                Pair("Android Title 2 - Emphasized", DesignTheme.typography.title2Emphasized)
            ),
            listOf(
                Pair("Android Title 3 - Regular", DesignTheme.typography.title3Regular),
                Pair("Android Title 3 - Emphasized", DesignTheme.typography.title3Emphasized)
            ),
            listOf(
                Pair("Android Headline - Regular", DesignTheme.typography.headlineRegular),
                Pair("Android Headline - Emphasized", DesignTheme.typography.headlineEmphasized)
            ),
            listOf(
                Pair("Android Body 1 - Regular", DesignTheme.typography.bodyRegular),
                Pair("Android Body 1 - Emphasized", DesignTheme.typography.bodyEmphasized)
            ),
            listOf(
                Pair("Android Body 2 - Regular", DesignTheme.typography.calloutRegular),
                Pair("Android Body 2 - Emphasized", DesignTheme.typography.calloutEmphasized)
            ),
            listOf(
                Pair("Android Subheadline - Regular", DesignTheme.typography.subHeadlineRegular),
                Pair("Android Subheadline - Emphasized", DesignTheme.typography.subHeadlineEmphasized)
            ),
            listOf(
                Pair("Android label 1 - Regular", DesignTheme.typography.footnoteRegular),
                Pair("Android label 1 - Emphasized", DesignTheme.typography.footnoteEmphasized)
            ),
            listOf(
                Pair("Android label 2 - Regular", DesignTheme.typography.captionRegular)
            )
        )

        Column(
            modifier = Modifier
                .background(DesignTheme.colors.backgroundColors.bgBase)
                .padding(
                    start = DesignTheme.spaces.spaceM,
                    top = DesignTheme.spaces.spaceM,
                    end = DesignTheme.spaces.spaceM,
                    bottom = DesignTheme.spaces.spaceM
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            typographies.forEach {
                CreateTypoGraphy(it)
                if (it != typographies.last()) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(DesignTheme.spaces.space5XS)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(DesignTheme.spaces.space5XS)
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateTypoGraphy(typographies: List<Pair<String, TextStyle>>) {
    typographies.forEach {
        Text(
            text = it.first,
            style = it.second
        )
        if (it != typographies.last()) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DesignTheme.spaces.space5XS)
            )
        }
    }
}
