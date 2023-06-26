package io.github.edwinchang24.salvage.feature.sharetarget

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.core.data.repository.ItemRepository
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val Url = "url"

@HiltViewModel
class ShareTargetActivityViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val url = savedStateHandle.getStateFlow(Url, "")

    fun onUrlChanged(url: String) {
        savedStateHandle[Url] = url
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.addItem(item)
        }
    }
}
