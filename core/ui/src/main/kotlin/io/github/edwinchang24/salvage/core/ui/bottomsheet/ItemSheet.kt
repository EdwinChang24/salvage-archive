package io.github.edwinchang24.salvage.core.ui.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.datetime.Instant
import kotlin.random.Random

@Composable
fun ItemSheet(item: Item, onDeleteItem: () -> Unit, onDismissBottomSheet: () -> Unit) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(item.name ?: "Unnamed", style = MaterialTheme.typography.titleMedium)
        Text(item.url, style = MaterialTheme.typography.bodyMedium)
    }
    Divider()
    ListItem(
        headlineContent = { Text("Delete") },
        leadingContent = { Icon(Icons.Default.DeleteForever, contentDescription = "Delete") },
        modifier = Modifier.clickable(onClick = {
            onDeleteItem()
            onDismissBottomSheet()
        })
    )
}

@Preview
@Composable
private fun ItemSheetPreview() {
    MaterialTheme {
        Surface {
            Column {
                ItemSheet(
                    item = Item(
                        id = "abcde",
                        name = "Article Title",
                        url = "https://example.com",
                        description = null,
                        timeAdded = Instant.fromEpochSeconds(Random.nextLong()),
                        timePublished = Instant.fromEpochSeconds(Random.nextLong())
                    ),
                    onDeleteItem = {},
                    onDismissBottomSheet = {}
                )
            }
        }
    }
}
