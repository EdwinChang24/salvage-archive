package io.github.edwinchang24.salvage.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.edwinchang24.salvage.core.database.converters.InstantConverter
import io.github.edwinchang24.salvage.core.database.dao.ItemDao
import io.github.edwinchang24.salvage.core.database.model.ItemEntity

@Database(
    entities = [ItemEntity::class],
    version = 1,
    autoMigrations = [],
    exportSchema = true
)
@TypeConverters(InstantConverter::class)
abstract class SalvageDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
