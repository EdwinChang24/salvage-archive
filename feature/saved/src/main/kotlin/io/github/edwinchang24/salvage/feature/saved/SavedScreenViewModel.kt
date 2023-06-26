package io.github.edwinchang24.salvage.feature.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.core.data.repository.ItemRepository
import io.github.edwinchang24.salvage.core.ui.ItemListUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SavedScreenViewModel @Inject constructor(itemRepository: ItemRepository) : ViewModel() {
    val listState: StateFlow<ItemListUiState> = itemRepository.getItems().map { ItemListUiState.Success(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ItemListUiState.Loading
    )
}
