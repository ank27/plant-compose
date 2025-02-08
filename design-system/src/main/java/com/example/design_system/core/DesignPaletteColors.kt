package com.example.design_system.core

import android.content.Context
import androidx.compose.ui.graphics.Color

internal object DesignPaletteColors {
    val gray0 = Color.White
    val gray0_50 = Color(0x80FFFFFF)
    val gray0_75 = Color(0xBFFFFFFF)
    val gray10 = Color(0xFFF8F8F8)
    val gray25 = Color(0xFFF0F0F0)
    val gray50 = Color(0xFFDFDFDF)
    val gray100 = Color(0xFFCECECE)
    val gray200 = Color(0xFFACACAC)
    val gray300 = Color(0xFF8A8A8A)
    val gray400 = Color(0xFF6C6C6C)
    val gray500 = Color(0xFF3A3A3C)
    val gray600 = Color(0xFF3A3A3C)
    val gray700 = Color(0xFF2C2C2E)
    val gray800 = Color(0xFF1C1C1E)
    val gray800_50 = Color(0x801C1C1E)
    val gray900 = Color(0xFF000000)
    val gray900_75 = Color(0xBF000000)

    // red color shades
    val red25 = Color(0xFFF9DADA)
    val red100 = Color(0xFFECA9AA)
    val red200 = Color(0xFFE59192)
    val red300 = Color(0xFFD96161)
    val red500 = Color(0xFFBF0001)
    val red600 = Color(0xFF990001)
    val red700 = Color(0xFF730001)
    val red800 = Color(0xFF390000)

    // yellow color shades
    val yellow25 = Color(0xFFFFF4DB)
    val yellow100 = Color(0xFFFFE6B2)
    val yellow200 = Color(0xFFFFDB8F)
    val yellow300 = Color(0xFFFFCD66)
    val yellow400 = Color(0xFFFFDB33)
    val yellow500 = Color(0xFFFFAC00)
    val yellow600 = Color(0xFFE59C00)
    val yellow700 = Color(0xFFA87200)
    val yellow900 = Color(0xFF422D00)

    // Green color shades
    val green25 = Color(0xFFD4F6E0)
    val green100 = Color(0xFFA5E4BB)
    val green200 = Color(0xFF8EDBA8)
    val green300 = Color(0xFF5EC983)
    val green500 = Color(0xFF00A538)
    val green600 = Color(0xFF006322)
    val green700 = Color(0xFF004216)
    val green900 = Color(0xFF00210B)

    // Blue color shades
    val blue25 = Color(0xFFDEEFFF)
    val blue100 = Color(0XFFB2D9FF)
    val blue200 = Color(0xFFA0D0FF)
    val blue300 = Color(0xFF78BCFF)
    val blue500 = Color(0xFF2693FF)
    val blue600 = Color(0xFF1E7FDE)
    val blue700 = Color(0xFF0F569B)
    val blue900 = Color(0xFF002D59)

    // Coral color shades
    val coral25 = Color(0xFFFCEEEE)
    val coral100 = Color(0xFFF7D5D5)
    val coral200 = Color(0xFFF5C7C7)
    val coral300 = Color(0xFFEFABAB)
    val coral500 = Color(0xFFE57373)
    val coral600 = Color(0xFFBF5E5E)
    val coral700 = Color(0xFF743333)
    val coral900 = Color(0xFF290808)

    // Teal color shades
    val teal25 = Color(0xFFCAF0ED)
    val teal100 = Color(0xFFA2DDD8)
    val teal200 = Color(0xFF8ED3CE)
    val teal300 = Color(0xFF67C0B8)
    val teal500 = Color(0xFF17998E)
    val teal600 = Color(0xFF127A72)
    val teal700 = Color(0xFF0E5C55)
    val teal900 = Color(0xFF051F1C)

    // Purple color shades
    val purple25 = Color(0xFFE7E2F8)
    val purple100 = Color(0xFFCAC1F1)
    val purple200 = Color(0xFFB4A7EC)
    val purple300 = Color(0xFF907DE3)
    val purple500 = Color(0xFF5032D2)
    val purple600 = Color(0xFF432AB1)
    val purple700 = Color(0xFF342088)
    val purple900 = Color(0xFF100A2C)
}

interface BackgroundColors {
    val bgBase: Color
    val bgBlur: Color
    val bgElevated: Color
    val bgTopbar: Color
}

interface FieldColors {
    val primary: Color
    val primaryPressed: Color
    val primaryDisabled: Color
    val secondary: Color
    val secondaryHover: Color
    val secondaryPressed: Color
    val secondaryDisabled: Color
    val inverse: Color
    val inverseHover: Color
    val inversePressed: Color
    val inverseDisabled: Color
}

interface TextColors {
    val primary: Color
    val secondary: Color
    val placeholder: Color
    val onPrimaryField: Color
    val onSecondaryField: Color
    val onPrimaryFieldDisabled: Color
    val onSecondaryFieldDisabled: Color
    val disabled: Color
    val onInverseField: Color
    val onInverseFieldDisabled: Color
}

interface IconColors {
    val standard: Color
    val protected: Color
    val warning: Color
    val danger: Color
    val active: Color
    val disabled: Color
    val onPrimaryField: Color
    val onPrimaryFieldDisabled: Color
    val onSecondaryFieldDisabled: Color
    val onFieldInverse: Color
    val onFieldInverseDisabled: Color
}

interface StatusColors {
    val error: Color
    val errorBg: Color
    val errorBorder: Color
    val errorText: Color

    val warning: Color
    val warningBg: Color
    val warningBorder: Color
    val warningText: Color

    val info: Color
    val infoBg: Color
    val infoBorder: Color
    val infoText: Color

    val success: Color
    val successBg: Color
    val successBorder: Color
    val successText: Color
}

interface BorderColors {
    val primary: Color
    val primaryPressed: Color
    val primaryDisabled: Color
    val secondary: Color
    val secondaryDisabled: Color
    val secondaryPressed: Color
    val tertiary: Color
    val tertiaryPressed: Color
    val tertiaryDisabled: Color
}

class DesignColorTheme(context: Context) {
    var coreCustomizedLight: Color = Color.Blue
    var coreCustomizedDark: Color = Color.Black
    var corePressedCustomizedLight: Color = Color.Blue
    var corePressedCustomizedDark: Color = Color.Black
    var bgBaseCustomizedLight: Color = Color.White
    var bgBaseCustomizedDark: Color = Color.Black

    val lightBackgroundColors = object : BackgroundColors {
        override val bgBase: Color
            get() = bgBaseCustomizedLight
        override val bgBlur: Color
            get() = DesignPaletteColors.gray0_50
        override val bgElevated: Color
            get() = DesignPaletteColors.gray0
        override val bgTopbar: Color
            get() = Color.White.copy(alpha = 0f)
    }

    val darkBackgroundColors = object : BackgroundColors {
        override val bgBase: Color
            get() = bgBaseCustomizedDark
        override val bgBlur: Color
            get() = DesignPaletteColors.gray800_50
        override val bgElevated: Color
            get() = DesignPaletteColors.gray800
        override val bgTopbar: Color
            get() = Color.Black.copy(alpha = 0f)
    }

    val lightFieldColors = object : FieldColors {
        override val primary: Color
            get() = coreCustomizedLight
        override val primaryPressed: Color
            get() = corePressedCustomizedLight
        override val primaryDisabled: Color
            get() = DesignPaletteColors.gray200
        override val secondary: Color
            get() = DesignPaletteColors.gray0
        override val secondaryHover: Color
            get() = DesignPaletteColors.gray200
        override val secondaryPressed: Color
            get() = DesignPaletteColors.gray50
        override val secondaryDisabled: Color
            get() = DesignPaletteColors.gray25
        override val inverse: Color
            get() = DesignPaletteColors.gray900_75
        override val inverseHover: Color
            get() = DesignPaletteColors.gray700
        override val inversePressed: Color
            get() = DesignPaletteColors.gray600
        override val inverseDisabled: Color
            get() = DesignPaletteColors.gray800
    }

    val darkFieldColors = object : FieldColors {
        override val primary: Color
            get() = coreCustomizedDark
        override val primaryPressed: Color
            get() = corePressedCustomizedDark
        override val primaryDisabled: Color
            get() = DesignPaletteColors.gray400
        override val secondary: Color
            get() = DesignPaletteColors.gray800
        override val secondaryHover: Color
            get() = DesignPaletteColors.gray400
        override val secondaryPressed: Color
            get() = DesignPaletteColors.gray600
        override val secondaryDisabled: Color
            get() = DesignPaletteColors.gray800
        override val inverse: Color
            get() = DesignPaletteColors.gray0_75
        override val inverseHover: Color
            get() = DesignPaletteColors.gray10
        override val inversePressed: Color
            get() = DesignPaletteColors.gray50
        override val inverseDisabled: Color
            get() = DesignPaletteColors.gray25
    }

    val lightTextColors = object : TextColors {
        override val primary: Color
            get() = DesignPaletteColors.gray900
        override val secondary: Color
            get() = DesignPaletteColors.gray600
        override val placeholder: Color
            get() = DesignPaletteColors.gray500
        override val onPrimaryField: Color
            get() = DesignPaletteColors.gray0
        override val onSecondaryField: Color
            get() = coreCustomizedLight
        override val onPrimaryFieldDisabled: Color
            get() = DesignPaletteColors.gray700
        override val onSecondaryFieldDisabled: Color
            get() = DesignPaletteColors.gray500
        override val disabled: Color
            get() = DesignPaletteColors.gray300
        override val onInverseField: Color
            get() = DesignPaletteColors.gray0
        override val onInverseFieldDisabled: Color
            get() = DesignPaletteColors.gray400
    }

    val darkTextColors = object : TextColors {
        override val primary: Color
            get() = DesignPaletteColors.gray0
        override val secondary: Color
            get() = DesignPaletteColors.gray100
        override val placeholder: Color
            get() = DesignPaletteColors.gray300
        override val onPrimaryField: Color
            get() = DesignPaletteColors.gray900
        override val onSecondaryField: Color
            get() = coreCustomizedDark
        override val onPrimaryFieldDisabled: Color
            get() = DesignPaletteColors.gray100
        override val onSecondaryFieldDisabled: Color
            get() = DesignPaletteColors.gray300
        override val disabled: Color
            get() = DesignPaletteColors.gray400
        override val onInverseField: Color
            get() = DesignPaletteColors.gray900
        override val onInverseFieldDisabled: Color
            get() = DesignPaletteColors.gray300
    }

    val lightBorderColors = object : BorderColors {
        override val primary: Color
            get() = coreCustomizedLight
        override val primaryPressed: Color
            get() = corePressedCustomizedLight
        override val primaryDisabled: Color
            get() = DesignPaletteColors.gray300
        override val secondary: Color
            get() = DesignPaletteColors.gray600
        override val secondaryDisabled: Color
            get() = DesignPaletteColors.gray100
        override val secondaryPressed: Color
            get() = DesignPaletteColors.gray800
        override val tertiary: Color
            get() = DesignPaletteColors.gray100
        override val tertiaryPressed: Color
            get() = DesignPaletteColors.gray300
        override val tertiaryDisabled: Color
            get() = DesignPaletteColors.gray100
    }

    val darkBorderColors = object : BorderColors {
        override val primary: Color
            get() = coreCustomizedDark
        override val primaryPressed: Color
            get() = corePressedCustomizedDark
        override val primaryDisabled: Color
            get() = DesignPaletteColors.gray500
        override val secondary: Color
            get() = DesignPaletteColors.gray200
        override val secondaryDisabled: Color
            get() = DesignPaletteColors.gray700
        override val secondaryPressed: Color
            get() = DesignPaletteColors.gray50
        override val tertiary: Color
            get() = DesignPaletteColors.gray600
        override val tertiaryPressed: Color
            get() = DesignPaletteColors.gray400
        override val tertiaryDisabled: Color
            get() = DesignPaletteColors.gray700
    }

    val lightStatusColors = object : StatusColors {
        override val error: Color
            get() = DesignPaletteColors.red500
        override val errorBg: Color
            get() = DesignPaletteColors.red25
        override val errorBorder: Color
            get() = DesignPaletteColors.red300
        override val errorText: Color
            get() = DesignPaletteColors.red600
        override val warning: Color
            get() = DesignPaletteColors.yellow500
        override val warningBg: Color
            get() = DesignPaletteColors.yellow25
        override val warningBorder: Color
            get() = DesignPaletteColors.yellow300
        override val warningText: Color
            get() = DesignPaletteColors.yellow700
        override val info: Color
            get() = DesignPaletteColors.blue500
        override val infoBg: Color
            get() = DesignPaletteColors.blue25
        override val infoBorder: Color
            get() = DesignPaletteColors.blue300
        override val infoText: Color
            get() = DesignPaletteColors.blue600
        override val success: Color
            get() = DesignPaletteColors.green500
        override val successBg: Color
            get() = DesignPaletteColors.green25
        override val successBorder: Color
            get() = DesignPaletteColors.green300
        override val successText: Color
            get() = DesignPaletteColors.green600
    }

    val darkStatusColors = object : StatusColors {
        override val error: Color
            get() = DesignPaletteColors.red200
        override val errorBg: Color
            get() = DesignPaletteColors.red800
        override val errorBorder: Color
            get() = DesignPaletteColors.red700
        override val errorText: Color
            get() = DesignPaletteColors.red100
        override val warning: Color
            get() = DesignPaletteColors.yellow200
        override val warningBg: Color
            get() = DesignPaletteColors.yellow900
        override val warningBorder: Color
            get() = DesignPaletteColors.yellow700
        override val warningText: Color
            get() = DesignPaletteColors.yellow100
        override val info: Color
            get() = DesignPaletteColors.blue200
        override val infoBg: Color
            get() = DesignPaletteColors.blue900
        override val infoBorder: Color
            get() = DesignPaletteColors.blue700
        override val infoText: Color
            get() = DesignPaletteColors.blue100
        override val success: Color
            get() = DesignPaletteColors.green200
        override val successBg: Color
            get() = DesignPaletteColors.green900
        override val successBorder: Color
            get() = DesignPaletteColors.green700
        override val successText: Color
            get() = DesignPaletteColors.green100
    }

    val lightIconColors = object : IconColors {
        override val standard: Color
            get() = DesignPaletteColors.gray900
        override val protected: Color
            get() = DesignPaletteColors.green500
        override val warning: Color
            get() = DesignPaletteColors.yellow600
        override val danger: Color
            get() = DesignPaletteColors.red500
        override val active: Color
            get() = coreCustomizedLight
        override val disabled: Color
            get() = DesignPaletteColors.gray300
        override val onPrimaryField: Color
            get() = DesignPaletteColors.gray0
        override val onPrimaryFieldDisabled: Color
            get() = DesignPaletteColors.gray700
        override val onSecondaryFieldDisabled: Color
            get() = DesignPaletteColors.gray500
        override val onFieldInverse: Color
            get() = DesignPaletteColors.gray0
        override val onFieldInverseDisabled: Color
            get() = DesignPaletteColors.gray400
    }

    val darkIconColors = object : IconColors {
        override val standard: Color
            get() = DesignPaletteColors.gray0
        override val protected: Color
            get() = DesignPaletteColors.green200
        override val warning: Color
            get() = DesignPaletteColors.yellow200
        override val danger: Color
            get() = DesignPaletteColors.red200
        override val active: Color
            get() = coreCustomizedDark
        override val disabled: Color
            get() = DesignPaletteColors.gray400
        override val onPrimaryField: Color
            get() = DesignPaletteColors.gray900
        override val onPrimaryFieldDisabled: Color
            get() = DesignPaletteColors.gray100
        override val onSecondaryFieldDisabled: Color
            get() = DesignPaletteColors.gray300
        override val onFieldInverse: Color
            get() = DesignPaletteColors.gray900
        override val onFieldInverseDisabled: Color
            get() = DesignPaletteColors.gray300
    }
}
