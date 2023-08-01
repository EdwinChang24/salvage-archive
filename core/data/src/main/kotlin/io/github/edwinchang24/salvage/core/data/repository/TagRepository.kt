package io.github.edwinchang24.salvage.core.data.repository

import io.github.edwinchang24.salvage.core.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun getTag(id: String): Flow<Tag>
    fun getTags(): Flow<List<Tag>>
    suspend fun addTag(tag: Tag)
    suspend fun deleteTag(tag: Tag)
    suspend fun updateTag(tag: Tag)
}
