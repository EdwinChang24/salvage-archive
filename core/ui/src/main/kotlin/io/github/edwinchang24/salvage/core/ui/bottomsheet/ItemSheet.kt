package io.github.edwinchang24.salvage.core.ui.bottomsheet

import androidx.annotation.ColorInt
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.core.model.Item
import io.github.edwinchang24.salvage.core.ui.customtabs.launchCustomTab
import kotlinx.datetime.Instant
import kotlin.random.Random

@Composable
fun ItemSheet(
    item: Item,
    onEditItem: (itemId: String) -> Unit,
    onDeleteItem: () -> Unit,
    onDismissBottomSheet: () -> Unit
) {
    Column {
        val context = LocalContext.current
        @ColorInt val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
        Column(modifier = Modifier.padding(10.dp)) {
            Text(item.name ?: "Unnamed", style = MaterialTheme.typography.titleMedium)
            Text(item.url, style = MaterialTheme.typography.bodyMedium)
        }
        Divider()
        ListItem(
            headlineContent = { Text("Open") },
            leadingContent = { Icon(Icons.Default.OpenInBrowser, contentDescription = "Open") },
            modifier = Modifier.clickable(
                onClick = {
                    launchCustomTab(context, url = item.url, barColor = backgroundColor)
                    onDismissBottomSheet()
                }
            )
        )
        ListItem(
            headlineContent = { Text("Edit") },
            leadingContent = { Icon(Icons.Default.Edit, contentDescription = "Edit") },
            modifier = Modifier.clickable(
                onClick = {
                    onEditItem(item.id)
                    onDismissBottomSheet()
                }
            )
        )
        ListItem(
            headlineContent = { Text("Delete") },
            leadingContent = { Icon(Icons.Default.DeleteForever, contentDescription = "Delete") },
            modifier = Modifier.clickable(
                onClick = {
                    onDeleteItem()
                    onDismissBottomSheet()
                }
            )
        )
    }
}

@Preview
@Composable
private fun ItemSheetPreview() {
    MaterialTheme {
        Surface {
            ItemSheet(
                item = Item(
                    id = "abcde",
                    name = "Article Title",
                    url = "https://example.com",
                    description = null,
                    timeAdded = Instant.fromEpochSeconds(Random.nextLong()),
                    timePublished = Instant.fromEpochSeconds(Random.nextLong())
                ),
                onEditItem = {},
                onDeleteItem = {},
                onDismissBottomSheet = {}
            )
        }
    }
}
