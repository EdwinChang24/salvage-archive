package io.github.edwinchang24.salvage.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import io.github.edwinchang24.salvage.feature.itemediting.newitem.navigateToNewItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(rootNavController: NavController, content: @Composable (contentPadding: PaddingValues) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Salvage") },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = rootNavController::navigateToNewItem) {
                Icon(Icons.Default.Add, contentDescription = "New item")
            }
        },
        content = content,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    )
}
