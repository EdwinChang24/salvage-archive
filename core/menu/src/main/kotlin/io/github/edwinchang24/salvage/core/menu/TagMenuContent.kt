package io.github.edwinchang24.salvage.core.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class TagMenuContent(private val onEditTag: () -> Unit, private val onDeleteTag: () -> Unit) : MenuContent {
    override val items: List<MenuItem>
        get() = listOf(
            MenuItem(
                label = "Edit",
                icon = Icons.Default.Edit,
                onSelect = onEditTag
            ),
            MenuItem(
                label = "Delete",
                icon = Icons.Default.DeleteForever,
                onSelect = onDeleteTag
            )
        )
    override val extraContent: @Composable ((modifier: Modifier) -> Unit)?
        get() = null
}
