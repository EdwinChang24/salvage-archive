package io.github.edwinchang24.salvage.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items

fun LazyGridScope.itemList(state: ItemListUiState) = when (state) {
    ItemListUiState.Loading -> Unit
    is ItemListUiState.Success -> items(state.list, key = { it.id }) {
        ItemCard(item = it)
    }
}
