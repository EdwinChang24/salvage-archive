package io.github.edwinchang24.salvage.core.ui.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.core.menu.MenuContent

@Composable
fun BottomSheetContent(menuContent: MenuContent, modifier: Modifier = Modifier) {
    val bottomSheetState = LocalSalvageBottomSheetState.current
    Column(modifier = modifier) {
        if (menuContent.extraContent != null) {
            menuContent.extraContent?.let { it(modifier = Modifier.padding(16.dp)) }
            Divider()
        }
        for (menuItem in menuContent.items) {
            ListItem(
                headlineContent = { Text(menuItem.label) },
                leadingContent = menuItem.icon?.let { { Icon(it, contentDescription = menuItem.label) } },
                modifier = Modifier.clickable(
                    onClick = {
                        menuItem.onSelect()
                        bottomSheetState.hideBottomSheet()
                    }
                )
            )
        }
    }
}
