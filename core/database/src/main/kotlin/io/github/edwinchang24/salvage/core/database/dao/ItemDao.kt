package io.github.edwinchang24.salvage.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.edwinchang24.salvage.core.database.model.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert
    fun insertAll(vararg items: ItemEntity)

    @Update
    fun update(vararg items: ItemEntity)

    @Delete
    fun delete(vararg items: ItemEntity)

    @Query(
        """
        SELECT * from items
        WHERE id = :id
        """
    )
    fun getItemEntity(id: String): Flow<ItemEntity>

    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<ItemEntity>>
}
