package io.github.edwinchang24.salvage.core.data.repository

import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItem(id: String): Flow<Item>
    fun getItems(): Flow<List<Item>>
    suspend fun addItem(item: Item)
}
