package io.github.edwinchang24.salvage.feature.itemediting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.core.data.repository.ItemRepository
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
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

    val existingItemId: StateFlow<String?> = savedStateHandle.getStateFlow(ExistingItemId, null)
    val name = savedStateHandle.getStateFlow(Name, "")
    val url = savedStateHandle.getStateFlow(Url, "")
    val description = savedStateHandle.getStateFlow(Description, "")

    fun setExistingItemId(id: String) {
        savedStateHandle[ExistingItemId] = id
        viewModelScope.launch(Dispatchers.IO) {
            val existingItem = itemRepository.getItem(id).firstOrNull()
            savedStateHandle[Name] = existingItem?.name ?: ""
            savedStateHandle[Url] = existingItem?.url ?: ""
            savedStateHandle[Description] = existingItem?.description ?: ""
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

    fun submitItem() = viewModelScope.launch(Dispatchers.IO) {
        if (existingItemId.value == null) {
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
            existingItemId.value?.let {
                val existingItem = itemRepository.getItem(it).first()
                itemRepository.updateItem(
                    Item(
                        id = it,
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
