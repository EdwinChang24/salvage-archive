package io.github.edwinchang24.salvage.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.edwinchang24.salvage.database.model.ItemEntity

@Dao
interface ItemDao {
    @Insert
    fun insertAll(vararg items: ItemEntity)

    @Update
    fun update(vararg items: ItemEntity)

    @Delete
    fun delete(vararg items: ItemEntity)

    @Query("SELECT * FROM items")
    fun getAll(): List<ItemEntity>
}
