package io.github.edwinchang24.salvage.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.datetime.Instant

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val url: String,
    val description: String?,
    val timeAdded: Instant,
    val timePublished: Instant?
)

fun ItemEntity.asExternalModel() = Item(
    id = id,
    name = name,
    url = url,
    description = description,
    timeAdded = timeAdded,
    timePublished = timePublished
)
