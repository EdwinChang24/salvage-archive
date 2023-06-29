package io.github.edwinchang24.salvage.feature.newitem.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.core.data.repository.ItemRepository
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NewItemScreenViewModel @Inject constructor(private val itemRepository: ItemRepository) : ViewModel() {
    var sharedUrl: String? = null

    fun addItem(name: String, url: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.addItem(
                Item(
                    id = UUID.randomUUID().toString(),
                    name = if (name == "") null else name,
                    url = url,
                    description = if (description == "") null else description,
                    timeAdded = Clock.System.now(),
                    timePublished = null
                )
            )
        }
    }
}
