package io.github.edwinchang24.salvage.core.ui.items

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import io.github.edwinchang24.salvage.core.model.Item

fun LazyGridScope.itemList(state: ItemListUiState, onItemLongClick: (item: Item) -> Unit) = when (state) {
    ItemListUiState.Loading -> Unit
    is ItemListUiState.Success -> items(state.list, key = { it.id }) { item ->
        ItemCard(item = item, onItemLongClick = { onItemLongClick(item) })
    }
}
