package io.github.edwinchang24.salvage.core.ui.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.datetime.Instant
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemCard(item: Item, onItemLongClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(CardDefaults.shape)
            .combinedClickable(onLongClick = { onItemLongClick() }, onClick = {})
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = item.name ?: "Unnamed", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Published: ${item.timePublished?.epochSeconds ?: "unknown"}",
                style = MaterialTheme.typography.titleSmall
            )
            Text(text = item.description ?: "No description", style = MaterialTheme.typography.bodyLarge)
            Text(text = item.url, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Added: ${item.timeAdded.epochSeconds}", style = MaterialTheme.typography.bodySmall)
            Text(text = "id ${item.id}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun ItemCardPreview() {
    MaterialTheme {
        Surface {
            ItemCard(
                item = Item(
                    id = "abcde",
                    name = "Article Title",
                    url = "https://example.com",
                    description = "Libero debitis maxime harum fugit nulla sint eveniet ut. Voluptas adipisci veniam " +
                        "iusto. Voluptate recusandae corporis minima ut repellat error occaecati. Et ducimus " +
                        "praesentium consequuntur molestiae. Aut magni dignissimos quisquam voluptas culpa enim.",
                    timeAdded = Instant.fromEpochSeconds(Random.nextLong()),
                    timePublished = Instant.fromEpochSeconds(Random.nextLong())
                ),
                onItemLongClick = {}
            )
        }
    }
}