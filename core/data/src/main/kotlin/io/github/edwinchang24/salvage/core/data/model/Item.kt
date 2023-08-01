package io.github.edwinchang24.salvage.core.data.model

import io.github.edwinchang24.salvage.core.database.model.ItemEntity
import io.github.edwinchang24.salvage.core.model.Item

fun Item.asEntity() = ItemEntity(id, name, url, description, timeAdded, timePublished)
