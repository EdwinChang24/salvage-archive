package io.github.edwinchang24.salvage.feature.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.core.data.repository.TagRepository
import io.github.edwinchang24.salvage.core.ui.tags.TagListUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TagsScreenViewModel @Inject constructor(tagRepository: TagRepository) : ViewModel() {
    val listState: StateFlow<TagListUiState> = tagRepository.getTags().map { TagListUiState.Success(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TagListUiState.Loading
    )
}
