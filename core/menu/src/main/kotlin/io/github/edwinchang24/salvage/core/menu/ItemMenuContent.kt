package io.github.edwinchang24.salvage.core.menu

import android.content.Context
import androidx.annotation.ColorInt
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.edwinchang24.salvage.core.model.Item
import io.github.edwinchang24.salvage.core.util.launchCustomTab
import io.github.edwinchang24.salvage.core.util.shareUrl

class ItemMenuContent(
    private val context: Context,
    private val item: Item,
    private val onEditItem: () -> Unit,
    private val onDeleteItem: () -> Unit,
    @ColorInt private val backgroundColor: Int
) : MenuContent {
    override val items: List<MenuItem>
        get() = listOf(
            MenuItem(
                label = "Open",
                icon = Icons.Default.OpenInBrowser,
                onSelect = { launchCustomTab(context = context, url = item.url, barColor = backgroundColor) }
            ),
            MenuItem(
                label = "Share",
                icon = Icons.Default.Share,
                onSelect = { context.shareUrl(item.url, title = item.name) }
            ),
            MenuItem(
                label = "Edit",
                icon = Icons.Default.Edit,
                onSelect = onEditItem
            ),
            MenuItem(
                label = "Delete",
                icon = Icons.Default.DeleteForever,
                onSelect = onDeleteItem
            )
        )

    override val extraContent: @Composable ((modifier: Modifier) -> Unit)
        get() = { modifier ->
            Column(modifier = modifier) {
                Text(item.name ?: "Unnamed", style = MaterialTheme.typography.titleMedium)
                Text(item.url, style = MaterialTheme.typography.bodyMedium)
            }
        }
}
