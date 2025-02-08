package com.example.design_system.core

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.design_system.R
import com.example.design_system.composable.DesignTheme

object DesignIcons {
    object Status {
        object Error {
            val Line = R.drawable.error
            val Filled = R.drawable.error_1
        }
        object Info {
            val Line = R.drawable.info
            val Filled = R.drawable.info_1
        }
        object Warning {
            val Line = R.drawable.warning
            val Filled = R.drawable.warning_1
        }
        object Success {
            val Line = R.drawable.success_line
            val Filled = R.drawable.success_filled
        }
        object Check {
            val Size24 = R.drawable.check_24
            val Size16 = R.drawable.check_16
        }
        val Question = R.drawable.account

        val Notification = R.drawable.ic_notification
    }
    object Action {
        object Add {
            object Line {
                val Size_24 = R.drawable.add
                val Size_48 = R.drawable.add_2
            }
            object Filled {
                val Size_24 = R.drawable.add_1
                val Size_48 = R.drawable.add_3
            }
        }
        object AddCircle {
            object Line {
                val Size_24 = R.drawable.add_circle
                val Size_48 = R.drawable.add_circle_2
            }
            object Filled {
                val Size_24 = R.drawable.add_circle_1
                val Size_48 = R.drawable.add_circle_3
            }
        }
        object Settings {
            object Line {
                val Size_24 = R.drawable.setting
                val Size_48 = R.drawable.setting_line_48
            }
            object Filled {
                val Size_24 = R.drawable.setting_1
                val Size_48 = R.drawable.setting_filled_48
            }
        }
        object Show {
            val Line = R.drawable.show
            val Filled = R.drawable.show_1
        }
        object Hide {
            val Line = R.drawable.hide
            val Filled = R.drawable.hide_1
        }
        object Search {
            val Line = R.drawable.add
            val Filled = R.drawable.auto_fill
        }
        val Edit = R.drawable.edit

        val Copy = R.drawable.copy

        val Trash = R.drawable.trash

        val FilterIcon = R.drawable.filter_icon

        val LockIcon = R.drawable.lock_icon

        val CloudConnect = R.drawable.cloud_connect

        val AutoFill = R.drawable.auto_fill
    }

    object Navigation {
        object ChevronRight {
            val Line = R.drawable.chevron_right
            val Filled = R.drawable.chevron_right_1
        }
        object ChevronLeft {
            val Line = R.drawable.chevron_left
            val Filled = R.drawable.chevron_left_1
        }
        object ChevronDown {
            val Line = R.drawable.chevron_down
            val Filled = R.drawable.chevron_down_1
        }
        object ChevronUp {
            val Line = R.drawable.chevron_up
            val Filled = R.drawable.chevron_up_1
        }
        object DoubleChevronRight {
            val Line = R.drawable.double_chevron_right
            val Filled = R.drawable.double_chevron_right_1
        }
        object DoubleChevronLeft {
            val Line = R.drawable.double_chevron_left
            val Filled = R.drawable.double_chevron_left_1
        }
        object DoubleChevronDown {
            val Line = R.drawable.double_chevron_down
            val Filled = R.drawable.double_chevron_down_1
        }
        object DoubleChevronUp {
            val Line = R.drawable.double_chevron_up
            val Filled = R.drawable.double_chevron_up_1
        }
        object Close {
            val Line = R.drawable.close
            val Filled = R.drawable.close_1
        }
        object ArrowLeft {
            val Line = R.drawable.arrow_back_line
            val Filled = R.drawable.arrow_back_filled
        }
        object Unfold {
            val Line = R.drawable.unfold_line
            val Filled = R.drawable.unfold_filled
        }
        val Check = R.drawable.check
    }
}

@Composable
fun DesignIcon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
    iconType: IconType = IconType.PLACEHOLDER
) {
    Icon(
        bitmap = bitmap,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint ?: getIconColor(iconType)
    )
}

@Composable
fun DesignIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
    iconType: IconType = IconType.PLACEHOLDER
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint ?: getIconColor(iconType)
    )
}

@Composable
fun DesignIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
    iconType: IconType = IconType.PLACEHOLDER
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint ?: getIconColor(iconType)
    )
}

enum class IconType {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    PRIMARY_DISABLED,
    SECONDARY_DISABLED,
    TERTIARY_DISABLED,
    WARNING,
    DANGER_ERROR,
    SUCCESS_PROTECT,
    INFO,
    PLACEHOLDER
}

@Composable
private fun getIconColor(iconType: IconType): Color {
    return when (iconType) {
        IconType.PRIMARY -> DesignTheme.colors.iconColors.onPrimaryField
        IconType.SECONDARY -> DesignTheme.colors.iconColors.active
        IconType.TERTIARY -> DesignTheme.colors.iconColors.standard
        IconType.PRIMARY_DISABLED -> DesignTheme.colors.iconColors.onPrimaryFieldDisabled
        IconType.SECONDARY_DISABLED -> DesignTheme.colors.iconColors.onPrimaryFieldDisabled
        IconType.TERTIARY_DISABLED -> DesignTheme.colors.iconColors.disabled
        IconType.WARNING -> DesignTheme.colors.iconColors.warning
        IconType.DANGER_ERROR -> DesignTheme.colors.iconColors.danger
        IconType.SUCCESS_PROTECT -> DesignTheme.colors.iconColors.protected
        IconType.INFO -> DesignTheme.colors.statusColors.info
        IconType.PLACEHOLDER -> DesignTheme.colors.iconColors.standard
    }
}
