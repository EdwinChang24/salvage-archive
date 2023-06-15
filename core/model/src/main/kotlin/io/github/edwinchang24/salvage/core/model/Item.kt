package io.github.edwinchang24.salvage.core.model

import kotlinx.datetime.Instant

data class Item(
    val id: String,
    val name: String?,
    val url: String,
    val description: String?,
    val timeAdded: Instant,
    val timePublished: Instant?
)
