package io.github.edwinchang24.salvage.ui

import io.github.edwinchang24.salvage.core.model.Item

sealed interface ItemListUiState {
    object Loading : ItemListUiState
    data class Success(val list: List<Item>) : ItemListUiState
}
