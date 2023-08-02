package io.github.edwinchang24.salvage.feature.tagediting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.core.data.repository.TagRepository
import io.github.edwinchang24.salvage.core.model.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

const val ExistingTagId = "existingTagId"
const val Name = "name"
const val Color = "color"
const val Description = "description"

@HiltViewModel
class TagEditingScreenViewModel @Inject constructor(
    private val tagRepository: TagRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val existingTagId: StateFlow<String?> = savedStateHandle.getStateFlow(ExistingTagId, null)
    val name = savedStateHandle.getStateFlow(Name, "")
    val color = savedStateHandle.getStateFlow(Color, DefaultColor.RED)
    val description = savedStateHandle.getStateFlow(Description, "")

    fun setExistingTagId(id: String) {
        savedStateHandle[ExistingTagId] = id
        viewModelScope.launch(Dispatchers.IO) {
            val existingTag = tagRepository.getTag(id).firstOrNull()
            savedStateHandle[Name] = existingTag?.name ?: ""
            savedStateHandle[Color] = existingTag?.color ?: DefaultColor.RED
            savedStateHandle[Description] = existingTag?.description ?: ""
        }
    }

    fun onEditName(name: String) {
        savedStateHandle[Name] = name
    }

    fun onEditColor(color: DefaultColor) {
        savedStateHandle[Color] = color
    }

    fun onEditDescription(description: String) {
        savedStateHandle[Description] = description
    }

    fun submitTag() = viewModelScope.launch(Dispatchers.IO) {
        if (existingTagId.value == null) {
            tagRepository.addTag(
                Tag(
                    id = UUID.randomUUID().toString(),
                    name = name.value,
                    color = color.value.colorInt,
                    description = description.value.ifEmpty { null }
                )
            )
        } else {
            existingTagId.value?.let {
                val existingTag = tagRepository.getTag(it).first()
                tagRepository.updateTag(
                    Tag(
                        id = existingTag.id,
                        name = name.value,
                        color = color.value.colorInt,
                        description = description.value.ifEmpty { null }
                    )
                )
            }
        }
    }
}
