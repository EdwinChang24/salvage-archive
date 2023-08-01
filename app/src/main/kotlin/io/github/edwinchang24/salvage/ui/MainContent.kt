package io.github.edwinchang24.salvage.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import io.github.edwinchang24.salvage.feature.itemediting.startNewItemActivity
import io.github.edwinchang24.salvage.navigation.TopLevelDestination
import io.github.edwinchang24.salvage.navigation.isSelected
import io.github.edwinchang24.salvage.navigation.navigateTo
import io.github.edwinchang24.salvage.navigation.selectedTopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(navController: NavHostController, content: @Composable (contentPadding: PaddingValues) -> Unit) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(navController.selectedTopLevelDestination()?.label ?: "Salvage") },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavigationBar {
                for (topLevelDestination in TopLevelDestination.entries) {
                    NavigationBarItem(
                        selected = topLevelDestination.isSelected(navController),
                        onClick = { navController.navigateTo(topLevelDestination) },
                        icon = {
                            Icon(
                                if (topLevelDestination.isSelected(navController)) topLevelDestination.selectedIcon
                                else topLevelDestination.unselectedIcon, contentDescription = null
                            )
                        },
                        label = { Text(topLevelDestination.label) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = context::startNewItemActivity) {
                Icon(Icons.Default.Add, contentDescription = "New item")
            }
        },
        content = content,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    )
}
