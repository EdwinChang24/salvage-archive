package io.github.edwinchang24.salvage.data.repository

import io.github.edwinchang24.salvage.core.model.Item
import io.github.edwinchang24.salvage.data.model.asEntity
import io.github.edwinchang24.salvage.database.dao.ItemDao
import io.github.edwinchang24.salvage.database.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultItemRepository @Inject constructor(private val itemDao: ItemDao) : ItemRepository {
    override fun getItem(id: String) = itemDao.getItemEntity(id).map { it.asExternalModel() }
    override fun getItems(): Flow<List<Item>> =
        itemDao.getAll().map { it.map { itemEntity -> itemEntity.asExternalModel() } }

    override suspend fun addItem(item: Item) = itemDao.insertAll(item.asEntity())
}
