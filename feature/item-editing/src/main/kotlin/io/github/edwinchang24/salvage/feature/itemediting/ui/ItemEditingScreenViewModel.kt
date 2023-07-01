package io.github.edwinchang24.salvage.feature.itemediting.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.core.data.repository.ItemRepository
import io.github.edwinchang24.salvage.core.model.Item
import io.github.edwinchang24.salvage.feature.itemediting.edititem.ItemId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject

private const val Name = "name"
private const val Url = "url"
private const val Description = "description"

@HiltViewModel
class ItemEditingScreenViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val existingItemId: String? = savedStateHandle[ItemId]
    val name = savedStateHandle.getStateFlow(Name, "")
    val url = savedStateHandle.getStateFlow(Url, "")
    val description = savedStateHandle.getStateFlow(Description, "")

    init {
        if (existingItemId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val existingItem = itemRepository.getItem(existingItemId).first()
                savedStateHandle[Name] = existingItem.name ?: ""
                savedStateHandle[Url] = existingItem.url
                savedStateHandle[Description] = existingItem.description ?: ""
            }
        }
    }

    fun onEditName(name: String) {
        savedStateHandle[Name] = name
    }

    fun onEditUrl(url: String) {
        savedStateHandle[Url] = url
    }

    fun onEditDescription(description: String) {
        savedStateHandle[Description] = description
    }


    fun submitItem() {
        viewModelScope.launch(Dispatchers.IO) {
            if (existingItemId == null) {
                itemRepository.addItem(
                    Item(
                        id = UUID.randomUUID().toString(),
                        name = if (name.value == "") null else name.value,
                        url = url.value,
                        description = if (description.value == "") null else description.value,
                        timeAdded = Clock.System.now(),
                        timePublished = null
                    )
                )
            } else {
                val existingItem = itemRepository.getItem(existingItemId).first()
                itemRepository.updateItem(
                    Item(
                        id = existingItemId,
                        name = if (name.value == "") null else name.value,
                        url = url.value,
                        description = if (description.value == "") null else description.value,
                        timeAdded = existingItem.timeAdded,
                        timePublished = existingItem.timePublished
                    )
                )
            }
        }
    }
}
