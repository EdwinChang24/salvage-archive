package io.github.edwinchang24.salvage.core.data.repository

import io.github.edwinchang24.salvage.core.data.model.asEntity
import io.github.edwinchang24.salvage.core.database.dao.TagDao
import io.github.edwinchang24.salvage.core.database.model.asExternalModel
import io.github.edwinchang24.salvage.core.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTagRepository @Inject constructor(private val tagDao: TagDao) : TagRepository {
    override fun getTag(id: String) = tagDao.getTagEntity(id).map { it.asExternalModel() }
    override fun getTags(): Flow<List<Tag>> =
        tagDao.getAll().map { it.map { tagEntity -> tagEntity.asExternalModel() } }

    override suspend fun addTag(tag: Tag) = tagDao.insertAll(tag.asEntity())
    override suspend fun deleteTag(tag: Tag) = tagDao.delete(tag.asEntity())
    override suspend fun updateTag(tag: Tag) = tagDao.update(tag.asEntity())
}

