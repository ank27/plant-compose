package com.example.design_system.composable

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.design_system.core.BackgroundColors
import com.example.design_system.core.BorderColors
import com.example.design_system.core.DesignColorTheme
import com.example.design_system.core.FieldColors
import com.example.design_system.core.IconColors
import com.example.design_system.core.StatusColors
import com.example.design_system.core.TextColors
import com.example.design_system.core.designSpaces
import com.example.design_system.core.getDesignRadius
import com.example.design_system.core.getTypography

fun TextStyle.bold() = this.merge(
    TextStyle(
        fontWeight = FontWeight(600)
    )
)

val defaultBackgroundColors = object : BackgroundColors {
    override val bgBase: Color
        get() = Color.Unspecified
    override val bgBlur: Color
        get() = Color.Unspecified
    override val bgElevated: Color
        get() = Color.Unspecified
    override val bgTopbar: Color
        get() = Color.Transparent
}

val defaultFieldColors = object : FieldColors {
    override val primary: Color
        get() = Color.Unspecified
    override val primaryPressed: Color
        get() = Color.Unspecified
    override val primaryDisabled: Color
        get() = Color.Unspecified
    override val secondary: Color
        get() = Color.Unspecified
    override val secondaryHover: Color
        get() = Color.Unspecified
    override val secondaryPressed: Color
        get() = Color.Unspecified
    override val secondaryDisabled: Color
        get() = Color.Unspecified
    override val inverse: Color
        get() = Color.Unspecified
    override val inverseHover: Color
        get() = Color.Unspecified
    override val inversePressed: Color
        get() = Color.Unspecified
    override val inverseDisabled: Color
        get() = Color.Unspecified
}

val defaultIconColors = object : IconColors {
    override val standard: Color
        get() = Color.Unspecified
    override val protected: Color
        get() = Color.Unspecified
    override val warning: Color
        get() = Color.Unspecified
    override val danger: Color
        get() = Color.Unspecified
    override val active: Color
        get() = Color.Unspecified
    override val disabled: Color
        get() = Color.Unspecified
    override val onPrimaryField: Color
        get() = Color.Unspecified
    override val onPrimaryFieldDisabled: Color
        get() = Color.Unspecified
    override val onSecondaryFieldDisabled: Color
        get() = Color.Unspecified
    override val onFieldInverse: Color
        get() = Color.Unspecified
    override val onFieldInverseDisabled: Color
        get() = Color.Unspecified
}

val defaultTextColors = object : TextColors {
    override val primary: Color
        get() = Color.Unspecified
    override val secondary: Color
        get() = Color.Unspecified
    override val placeholder: Color
        get() = Color.Unspecified
    override val onPrimaryField: Color
        get() = Color.Unspecified
    override val onSecondaryField: Color
        get() = Color.Unspecified
    override val onPrimaryFieldDisabled: Color
        get() = Color.Unspecified
    override val onSecondaryFieldDisabled: Color
        get() = Color.Unspecified
    override val disabled: Color
        get() = Color.Unspecified
    override val onInverseField: Color
        get() = Color.Unspecified
    override val onInverseFieldDisabled: Color
        get() = Color.Unspecified
}

val defaultBorderColors = object : BorderColors {
    override val primary: Color
        get() = Color.Unspecified
    override val primaryPressed: Color
        get() = Color.Unspecified
    override val primaryDisabled: Color
        get() = Color.Unspecified
    override val secondary: Color
        get() = Color.Unspecified
    override val secondaryDisabled: Color
        get() = Color.Unspecified
    override val secondaryPressed: Color
        get() = Color.Unspecified
    override val tertiary: Color
        get() = Color.Unspecified
    override val tertiaryPressed: Color
        get() = Color.Unspecified
    override val tertiaryDisabled: Color
        get() = Color.Unspecified
}

val defaultStatusColors = object : StatusColors {
    override val error: Color
        get() = Color.Unspecified
    override val errorBg: Color
        get() = Color.Unspecified
    override val errorBorder: Color
        get() = Color.Unspecified
    override val errorText: Color
        get() = Color.Unspecified
    override val warning: Color
        get() = Color.Unspecified
    override val warningBg: Color
        get() = Color.Unspecified
    override val warningBorder: Color
        get() = Color.Unspecified
    override val warningText: Color
        get() = Color.Unspecified
    override val info: Color
        get() = Color.Unspecified
    override val infoBg: Color
        get() = Color.Unspecified
    override val infoBorder: Color
        get() = Color.Unspecified
    override val infoText: Color
        get() = Color.Unspecified
    override val success: Color
        get() = Color.Unspecified
    override val successBg: Color
        get() = Color.Unspecified
    override val successBorder: Color
        get() = Color.Unspecified
    override val successText: Color
        get() = Color.Unspecified
}

@Immutable
data class DesignThemeColors(
    val backgroundColors: BackgroundColors,
    val fieldColors: FieldColors,
    val iconColors: IconColors,
    val textColors: TextColors,
    val statusColors: StatusColors,
    val borderColors: BorderColors,
)

@Immutable
data class DesignSpaces(
    val spaceNone: Dp = 0.dp,
    val space5XS: Dp = 0.dp,
    val space4XS: Dp = 0.dp,
    val space3XS: Dp = 0.dp,
    val space2XS: Dp = 0.dp,
    val spaceXS: Dp = 0.dp,
    val spaceS: Dp = 0.dp,
    val spaceM: Dp = 0.dp,
    val spaceL: Dp = 0.dp,
    val spaceXL: Dp = 0.dp,
    val space2XL: Dp = 0.dp,
    val space3XL: Dp = 0.dp,
    val space4XL: Dp = 0.dp,
    val space5XL: Dp = 0.dp,
    val space6XL: Dp = 0.dp,
    val space7XL: Dp = 0.dp
)

@Immutable
data class DesignTypography(
    val largeTitleRegular: TextStyle = TextStyle(),
    val title1Regular: TextStyle = TextStyle(),
    val title2Regular: TextStyle = TextStyle(),
    val title3Regular: TextStyle = TextStyle(),
    val headlineRegular: TextStyle = TextStyle(),
    val bodyRegular: TextStyle = TextStyle(),
    val calloutRegular: TextStyle = TextStyle(),
    val subHeadlineRegular: TextStyle = TextStyle(),
    val footnoteRegular: TextStyle = TextStyle(),
    val captionRegular: TextStyle = TextStyle()
) {
    val largeTitleEmphasized: TextStyle
        get() = largeTitleRegular.bold()

    val title1Emphasized: TextStyle
        get() = title1Regular.bold()

    val title2Emphasized: TextStyle
        get() = title2Regular.bold()

    val title3Emphasized: TextStyle
        get() = title3Regular.bold()

    val headlineEmphasized: TextStyle
        get() = headlineRegular.bold()

    val bodyEmphasized: TextStyle
        get() = bodyRegular.bold()

    val calloutEmphasized: TextStyle
        get() = calloutRegular.bold()

    val subHeadlineEmphasized: TextStyle
        get() = subHeadlineRegular.bold()

    val footnoteEmphasized: TextStyle
        get() = footnoteRegular.bold()
}

@Immutable
data class DesignRadii(
    val radiusFull: Dp = 0.dp,
    val radiusL: Dp = 0.dp,
    val radiusM: Dp = 0.dp,
    val radiusS: Dp = 0.dp,
    val radiusXS: Dp = 0.dp,
    val radius2XS: Dp = 0.dp
)

val LocalDesignTypographies = staticCompositionLocalOf {
    DesignTypography()
}

val LocalDesignSpaces = staticCompositionLocalOf {
    DesignSpaces()
}

val LocalDesignRadii = staticCompositionLocalOf {
    DesignRadii()
}

val LocalDesignThemeColors = staticCompositionLocalOf {
    DesignThemeColors(
        backgroundColors = defaultBackgroundColors,
        fieldColors = defaultFieldColors,
        iconColors = defaultIconColors,
        textColors = defaultTextColors,
        borderColors = defaultBorderColors,
        statusColors = defaultStatusColors,
    )
}

/**
 * Design Theme refers to the customization of Google's Material Design to better reflect
 * product’s brand.
 *
 * All values are set with with the [colorScheme][DesignColorTheme],
 * [typography][DesignTypography] and [shapes][Shapes] attributes.
 *
 * Inspired by MaterialTheme from Jetpack Compose
 */
@Composable
fun DesignTheme(
    content: @Composable () -> Unit
) {
    val designColorTheme = DesignColorTheme(LocalContext.current)

    val designThemeColor = DesignThemeColors(
        backgroundColors = designColorTheme.lightBackgroundColors,
        fieldColors = designColorTheme.lightFieldColors,
        textColors = designColorTheme.lightTextColors,
        borderColors = designColorTheme.lightBorderColors,
        statusColors = designColorTheme.lightStatusColors,
        iconColors = designColorTheme.lightIconColors,
    )

    val designTypography = getTypography(designThemeColor.textColors.primary)
    val designSpaces = designSpaces
    val designRadii = getDesignRadius()

    val designColors = lightColorScheme(
        primary = designThemeColor.fieldColors.primary,
        secondary = designThemeColor.fieldColors.secondary,
        onPrimary = designThemeColor.textColors.onPrimaryField,
    )

    CompositionLocalProvider(
        LocalDesignThemeColors provides designThemeColor,
        LocalDesignTypographies provides designTypography,
        LocalDesignSpaces provides designSpaces,
        LocalDesignRadii provides designRadii,
    ) {
        MaterialTheme(
            colorScheme = designColors,
            shapes = shapes,
            content = content
        )
    }
}

/**
 * Contains functions to access the current theme values provided at the call site
 */
object DesignTheme {
    val colors: DesignThemeColors
        @Composable
        get() = LocalDesignThemeColors.current
    val typography: DesignTypography
        @Composable
        get() = LocalDesignTypographies.current
    val spaces: DesignSpaces
        @Composable
        get() = LocalDesignSpaces.current
    val radii: DesignRadii
        @Composable
        get() = LocalDesignRadii.current
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class ThemePreview
