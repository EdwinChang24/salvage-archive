package io.github.edwinchang24.salvage.core.menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

interface MenuContent {
    val items: List<MenuItem>
    val extraContent: @Composable ((modifier: Modifier) -> Unit)?
}

data class MenuItem(val label: String, val icon: ImageVector? = null, val onSelect: () -> Unit)
