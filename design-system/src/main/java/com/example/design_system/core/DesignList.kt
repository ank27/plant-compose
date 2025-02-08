package com.example.design_system.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.design_system.composable.DesignTheme
import com.example.design_system.composable.ThemePreview

data class ItemLayoutData(
    val itemLayoutMain: ItemLayoutMainData,
    val leadingComposable: (@Composable () -> Unit)? = null,
    val trailingComposable: (@Composable () -> Unit)? = null,
    val onClick: (() -> Unit)? = null
)

data class ItemStacks(
    val itemLayoutData: ItemLayoutData? = null,
    val itemStacksContent: (@Composable ColumnScope.() -> Unit)? = null,
    val onClick: (() -> Unit)? = null
)

data class ItemLayoutMainData(
    val headline: String? = null,
    val headlineColor: Color? = null,
    val subHeadline: String? = null,
    val subHeadlineOnClick: ((String) -> Unit)? = null,
    val bottomComposable: (@Composable () -> Unit)? = null
)

data class InlineListItemData(
    val leadingComposable: (@Composable () -> Unit),
    val annotatedBody: AnnotatedString? = null,
    val body: String? = null,
    val onClick: (() -> Unit)? = null,
)

@Composable
fun DesignInlineList(
    inlineListItems: List<InlineListItemData>,
) {
    Column {
        inlineListItems.map {
            InlineListItem(
                inlineListItemData = it
            )
        }
    }
}

@Composable
fun DesignList(
    isRefreshing: Boolean = false,
    title: String? = null,
    titleLeadingIcon: Int? = null,
    hideList: Boolean = false,
    background: Color = DesignTheme.colors.backgroundColors.bgElevated,
    titleTrailingComposable: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    itemStacksContent: @Composable ColumnScope.() -> Unit
) {
    CreateDesignList(
        title = title,
        background = background,
        isRefreshing = isRefreshing,
        titleLeadingIcon = titleLeadingIcon,
        titleTrailingComposable = titleTrailingComposable,
        hideList = hideList,
        onTitleClick = onClick
    ) {
        itemStacksContent()
    }
}

@Composable
fun ListItemStack(
    backgroundColor: Color = Color.Transparent,
    padding: Dp = DesignTheme.spaces.spaceM, // temp fix as in specs it should always have padding
    cornerRadius: Dp = 0.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    Column(
        modifier = Modifier
            .clip(
                shape
            )
            .clickable(
                enabled = onClick != null
            ) {
                onClick?.invoke()
            }
            .background(
                color = backgroundColor,
                shape = shape
            )
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
    ) {
        content()
    }
}

@Composable
fun ListItemLayout(
    itemLayoutData: ItemLayoutData,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceXS)
        ) {
            itemLayoutData.leadingComposable?.let {
                Box(
                    modifier = Modifier
                        .sizeIn(
                            minWidth = 48.dp,
                            minHeight = 48.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 48.dp),
                contentAlignment = Alignment.Center
            ) {
                ListItemMain(
                    itemLayoutMainData = itemLayoutData.itemLayoutMain,
                )
            }
            itemLayoutData.trailingComposable?.let {
                Box(
                    modifier = Modifier
                        .sizeIn(
                            minWidth = 48.dp,
                            minHeight = 48.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    it()
                }
            }
        }
    }
}

@Composable
fun ListItemMain(
    itemLayoutMainData: ItemLayoutMainData,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.space3XS)
    ) {
        itemLayoutMainData.headline?.let {
            Text(
                text = it,
                style = DesignTheme.typography.headlineEmphasized,
                color = itemLayoutMainData.headlineColor ?: DesignTheme.colors.textColors.primary,
                modifier = Modifier
            )
        }

        itemLayoutMainData.subHeadline
            ?.takeIf { subHeadline -> subHeadline.isNotEmpty() }
            ?.let { subHeadline ->
                // when sub headline click is null, assume that it is not HyperLinkText.
                Text(
                    text = subHeadline,
                    style = DesignTheme.typography.subHeadlineRegular,
                    color = DesignTheme.colors.textColors.primary,
                )
            }
        itemLayoutMainData.bottomComposable?.let {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                it()
            }
        }
    }
}

@Composable
private fun InlineListItem(
    inlineListItemData: InlineListItemData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = DesignTheme.spaces.spaceXS,
                bottom = DesignTheme.spaces.spaceXS
            ),
        horizontalArrangement = Arrangement.spacedBy(
            DesignTheme.spaces.space3XS,
            Alignment.Start
        ),
        verticalAlignment = Alignment.Top
    ) {
        inlineListItemData.leadingComposable.invoke()
        inlineListItemData.body?.let {
            Text(
                style = DesignTheme.typography.bodyRegular,
                text = it,
            )
        }
        inlineListItemData.annotatedBody?.let {
            Text(
                style = DesignTheme.typography.bodyRegular,
                text = it,
            )
        }
    }
}

@Composable
private fun CreateDesignList(
    title: String?,
    hideList: Boolean,
    isRefreshing: Boolean = false,
    titleLeadingIcon: Int? = null,
    background: Color = DesignTheme.colors.backgroundColors.bgElevated,
    titleTrailingComposable: @Composable (() -> Unit)? = null,
    onTitleClick: (() -> Unit)? = null,
    listItemStacks: @Composable ColumnScope.() -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(
            space = DesignTheme.spaces.space3XS,
            alignment = Alignment.Top
        )
    ) {
        title?.let {
            SectionTitle(
                title = it,
                leadingIcon = titleLeadingIcon,
                trailingComposable = titleTrailingComposable,
                onClick = onTitleClick
            )
        }
        AnimatedVisibility(
            visible = !hideList
        ) {
            val shape = RoundedCornerShape(size = DesignTheme.radii.radiusS)
            Column(
                modifier = Modifier
                    .clip(shape)
                    .background(
                        color = background,
                        shape = RoundedCornerShape(size = DesignTheme.radii.radiusS)
                    )
            ) {
                if (isRefreshing) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth().padding(DesignTheme.spaces.spaceM)
                    ) {
                        ProgressSpinner()
                    }
                } else {
                    listItemStacks()
                }
            }
        }
    }
}

@ThemePreview
@Composable
fun PreviewListItem1() {
    DesignTheme {
        ListItemStack(
            backgroundColor = DesignTheme.colors.backgroundColors.bgElevated,
        ) {
            ListItemLayout(
                ItemLayoutData(
                    itemLayoutMain = ItemLayoutMainData(
                        headline = "Headline Emphasized",
                        subHeadline = "Android Subheadline"
                    ),
                ),
            )
        }
    }
}

@ThemePreview
@Composable
fun PreviewListItem2() {
    DesignTheme {
        ListItemStack(
            backgroundColor = DesignTheme.colors.backgroundColors.bgElevated,
        ) {
            ListItemLayout(
                ItemLayoutData(
                    itemLayoutMain = ItemLayoutMainData(
                        headline = "Headline Emphasized",
                        subHeadline = "Android Subheadline",
                        bottomComposable = {
                            DesignButtons.SecondaryLargeButton(
                                label = "label",
                                enabled = true,
                                fillMaxWidth = true
                            ) {
                            }
                        }
                    ),
                ),
            )
        }
    }
}

private fun getListItems(): List<ItemLayoutData> {
    return mutableListOf<ItemLayoutData>().also { list ->
        repeat(3) {
            list.add(
                ItemLayoutData(
                    itemLayoutMain = ItemLayoutMainData(
                        headline = "Headline Emphasized ${it + 1}",
                        subHeadline = "Android Subheadline ${it + 1}"
                    ),
                    trailingComposable = {
                        Icon(
                            painter = painterResource(
                                id = DesignIcons.Navigation.ChevronRight.Line
                            ),
                            contentDescription = null,
                            tint = DesignTheme.colors.iconColors.standard.copy(alpha = 0.3f)
                        )
                    },
                )
            )
        }
        repeat(2) {
            list.add(
                ItemLayoutData(
                    itemLayoutMain = ItemLayoutMainData(
                        headline = "Headline Emphasized ${it + 1}",
                        subHeadline = "Android Subheadline ${it + 1}"
                    ),
                    trailingComposable = {
                        Icon(
                            painter = painterResource(
                                id = DesignIcons.Navigation.ChevronRight.Line
                            ),
                            contentDescription = null,
                            tint = DesignTheme.colors.iconColors.standard.copy(alpha = 0.3f)
                        )
                    },
                )
            )
        }
        repeat(3) {
            list.add(
                ItemLayoutData(
                    itemLayoutMain = ItemLayoutMainData(
                        headline = "Headline Emphasized ${it + 1}"
                    ),
                    trailingComposable = {
                        Icon(
                            painter = painterResource(
                                id = DesignIcons.Navigation.ChevronRight.Line
                            ),
                            contentDescription = null,
                            tint = DesignTheme.colors.iconColors.standard.copy(alpha = 0.3f)
                        )
                    },
                )
            )
        }
    }
}

@ThemePreview
@Composable
fun PreviewList() {
    DesignTheme {
        val listItems = getListItems()
        Box(modifier = Modifier.background(DesignTheme.colors.backgroundColors.bgBase)) {
            DesignList(
                title = "List group name",
            ) {
                listItems.forEach {
                    ListItemStack {
                        ListItemLayout(
                            itemLayoutData = it
                        )
                        DesignButtons.PrimaryLargeButton(
                            label = "Button",
                            fillMaxWidth = true,
                            enabled = true,
                        ) {
                        }
                    }
                }
            }
        }
    }
}
