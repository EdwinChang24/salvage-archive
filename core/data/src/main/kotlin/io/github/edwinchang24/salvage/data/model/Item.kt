package io.github.edwinchang24.salvage.data.model

import io.github.edwinchang24.salvage.core.model.Item
import io.github.edwinchang24.salvage.database.model.ItemEntity

fun Item.asEntity() = ItemEntity(
    id = id,
    name = name,
    url = url,
    description = description,
    timeAdded = timeAdded,
    timePublished = timePublished
)
