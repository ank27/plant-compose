package com.example.design_system.core

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

@Composable
fun DesignTheme(
    title: String,
    description: String,
    primaryButtonLabel: String,
    secondaryButtonLabel: String,
    illustration: Icon? = null,
    icon: Painter? = null,
    iconType: IconType? = null,
    centerTitle: Boolean? = false,
    titleTestTag: String = "",
    descriptionTestTag: String = "",
    primaryButtonTestTag: String = "",
    secondaryButtonTestTag: String = "",
    illustrationTestTag: String? = "",
    iconTestTag: String? = "",
    onDismiss: () -> Unit,
    primaryButtonOnClickListener: () -> Unit,
    secondaryButtonOnClickListener: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        DialogContent(
            title,
            description,
            primaryButtonLabel,
            secondaryButtonLabel,
            primaryButtonOnClickListener,
            secondaryButtonOnClickListener,
            illustration,
            icon,
            iconType,
            centerTitle,
            titleTestTag,
            descriptionTestTag,
            primaryButtonTestTag,
            secondaryButtonTestTag,
            illustrationTestTag,
            iconTestTag,
        )
    }
}

@Composable
fun DialogContent(
    title: String,
    description: String,
    primaryButtonLabel: String,
    secondaryButtonLabel: String,
    primaryButtonOnClickListener: () -> Unit,
    secondaryButtonOnClickListener: () -> Unit,
    illustration: Icon? = null,
    icon: Painter? = null,
    iconType: IconType? = null,
    centerTitle: Boolean? = false,
    titleTestTag: String = "",
    descriptionTestTag: String = "",
    primaryButtonTestTag: String = "",
    secondaryButtonTestTag: String = "",
    illustrationTestTag: String? = "",
    iconTestTag: String? = "",
) {
    val titleAlignment = if (centerTitle == true) {
        TextAlign.Center
    } else {
        TextAlign.Start
    }

    Surface(
        color = DesignTheme.colors.backgroundColors.bgElevated,
        shape = RoundedCornerShape(
            size = designSpaces.spaceM
        )
    ) {
        Column(
            modifier = Modifier
                .padding(designSpaces.spaceL)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                icon?.let { painter ->
                    iconType?.let { type ->
                        DesignIcon(
                            modifier = Modifier.testTag(iconTestTag ?: "")
                                .padding(bottom = DesignTheme.spaces.spaceM),
                            painter = painter,
                            contentDescription = null,
                            iconType = type
                        )
                    } ?: Image(
                        modifier = Modifier.testTag(iconTestTag ?: ""),
                        painter = icon,
                        contentDescription = null,
                    )
                }
            }

            Text(
                text = title,
                style = DesignTheme.typography.title2Regular,
                textAlign = titleAlignment,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(titleTestTag)
            )

            Spacer(modifier = Modifier.height(designSpaces.spaceM))

            Text(
                text = description,
                style = DesignTheme.typography.bodyRegular,
                color = DesignTheme.colors.textColors.secondary,
                modifier = Modifier
                    .testTag(
                        descriptionTestTag
                    )
            )

            Spacer(modifier = Modifier.height(designSpaces.spaceL))

            DesignButtons.PrimaryLargeButton(
                fillMaxWidth = true,
                label = primaryButtonLabel,
            ) {
                primaryButtonOnClickListener()
            }

            Spacer(modifier = Modifier.height(designSpaces.spaceXS))

            DesignButtons.SecondaryLargeButton(
                fillMaxWidth = true,
                label = secondaryButtonLabel,
            ) {
                secondaryButtonOnClickListener()
            }
        }
    }
}

@ThemePreview
@Composable
fun PreviewDialogContent() {
    DesignTheme {
        DialogContent(
            title = "Basic Dialog Title",
            description = "A dialog is a type of modal window that appears in front of app " +
                "content to provide critical information, or prompt for a decision to be made.",
            primaryButtonLabel = "Primary",
            secondaryButtonLabel = "Secondary",
            primaryButtonOnClickListener = {},
            secondaryButtonOnClickListener = {}
        )
    }
}

@ThemePreview
@Composable
fun PreviewDialogContentWithIcon() {
    DesignTheme {
        DialogContent(
            icon = painterResource(id = DesignIcons.Status.Warning.Line),
            iconType = IconType.WARNING,
            title = "Basic Dialog Title",
            description = "A dialog is a type of modal window that appears in front of app " +
                "content to provide critical information, or prompt for a decision to be made.",
            primaryButtonLabel = "Primary",
            secondaryButtonLabel = "Secondary",
            centerTitle = true,
            primaryButtonOnClickListener = {},
            secondaryButtonOnClickListener = {}
        )
    }
}

@ThemePreview
@Composable
fun PreviewDialogContentWithIllustration() {
    DesignTheme {
        DialogContent(
            icon = painterResource(id = DesignIcons.Status.Warning.Line),
            title = "Basic Dialog Title",
            description = "A dialog is a type of modal window that appears in front of app " +
                "content to provide critical information, or prompt for a decision to be made.",
            primaryButtonLabel = "Primary",
            secondaryButtonLabel = "Secondary",
            primaryButtonOnClickListener = {},
            secondaryButtonOnClickListener = {}
        )
    }
}
