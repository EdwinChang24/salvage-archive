package io.github.edwinchang24.salvage.core.ui.tags

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalFoundationApi::class)
fun LazyGridScope.tagList(state: TagListUiState, onTagClick: () -> Unit, onTagLongClick: () -> Unit) {
    if (state is TagListUiState.Success) {
        items(items = state.list, key = { it.id }) { tag ->
            ListItem(
                headlineContent = { Text(tag.name) },
                supportingContent = tag.description?.let { { Text(it) } },
                leadingContent = { Box(modifier = Modifier.background(Color(tag.color), shape = CircleShape)) },
                modifier = Modifier.combinedClickable(onClick = onTagClick, onLongClick = onTagLongClick)
            )
        }
    }
}
