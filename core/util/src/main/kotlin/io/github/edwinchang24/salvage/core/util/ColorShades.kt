package io.github.edwinchang24.salvage.core.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Color.adjusted(): Color {
    val backgroundColor = MaterialTheme.colorScheme.background
    return Color(
        red = (red * 2 + backgroundColor.red) / 3,
        green = (green * 2 + backgroundColor.green) / 3,
        blue = (blue * 2 + backgroundColor.blue) / 3
    )
}
