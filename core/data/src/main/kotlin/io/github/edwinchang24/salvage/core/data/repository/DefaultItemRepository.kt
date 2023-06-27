package io.github.edwinchang24.salvage.core.data.repository

import io.github.edwinchang24.salvage.core.data.model.asEntity
import io.github.edwinchang24.salvage.core.database.dao.ItemDao
import io.github.edwinchang24.salvage.core.database.model.asExternalModel
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultItemRepository @Inject constructor(private val itemDao: ItemDao) : ItemRepository {
    override fun getItem(id: String) = itemDao.getItemEntity(id).map { it.asExternalModel() }
    override fun getItems(): Flow<List<Item>> =
        itemDao.getAll().map { it.map { itemEntity -> itemEntity.asExternalModel() } }

    override suspend fun addItem(item: Item) = itemDao.insertAll(item.asEntity())

    override suspend fun deleteItem(item: Item) = itemDao.delete(item.asEntity())
}
