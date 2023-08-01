package io.github.edwinchang24.salvage.core.util

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.formatted(): String = LocalDateTime.ofInstant(toJavaInstant(), ZoneId.systemDefault()).format(
    DateTimeFormatter.ofPattern("M/d/y h:mm:ss a")
)
