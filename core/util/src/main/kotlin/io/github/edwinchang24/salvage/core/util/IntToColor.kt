package io.github.edwinchang24.salvage.core.util

import androidx.compose.ui.graphics.Color

fun Int.toColorOpaque() = Color(this).copy(alpha = 1.0f)
