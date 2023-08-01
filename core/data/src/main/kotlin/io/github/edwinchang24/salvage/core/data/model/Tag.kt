package io.github.edwinchang24.salvage.core.data.model

import io.github.edwinchang24.salvage.core.database.model.TagEntity
import io.github.edwinchang24.salvage.core.model.Tag

fun Tag.asEntity() = TagEntity(id, name, color, description)
