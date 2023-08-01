package io.github.edwinchang24.salvage.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.edwinchang24.salvage.core.database.model.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Insert
    fun insertAll(vararg tags: TagEntity)

    @Update
    fun update(vararg tags: TagEntity)

    @Delete
    fun delete(vararg tags: TagEntity)

    @Query(
        """
            SELECT * from tags
            WHERE id = :id
        """
    )
    fun getTagEntity(id: String): Flow<TagEntity>

    @Query("SELECT * from tags")
    fun getAll(): Flow<List<TagEntity>>
}
