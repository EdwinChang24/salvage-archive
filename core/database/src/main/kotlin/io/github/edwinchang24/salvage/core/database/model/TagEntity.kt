package io.github.edwinchang24.salvage.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.edwinchang24.salvage.core.model.Tag

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val color: Int,
    val description: String?
)

fun TagEntity.asExternalModel() = Tag(id, name, color, description)
