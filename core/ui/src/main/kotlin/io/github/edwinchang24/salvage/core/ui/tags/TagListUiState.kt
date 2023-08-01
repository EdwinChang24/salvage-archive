package io.github.edwinchang24.salvage.core.ui.tags

import io.github.edwinchang24.salvage.core.model.Tag

sealed interface TagListUiState {
    object Loading : TagListUiState
    data class Success(val list: List<Tag>) : TagListUiState
}
