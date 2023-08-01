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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.edwinchang24.salvage.feature.itemediting.newitem.navigateToNewItem
import io.github.edwinchang24.salvage.navigation.TopLevelDestination
import io.github.edwinchang24.salvage.navigation.isSelected
import io.github.edwinchang24.salvage.navigation.navigateTo
import io.github.edwinchang24.salvage.navigation.selectedTopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    rootNavController: NavController,
    content: @Composable (contentPadding: PaddingValues, mainNavController: NavHostController) -> Unit
) {
    val mainNavController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(mainNavController.selectedTopLevelDestination()?.label ?: "Salvage") },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavigationBar {
                for (topLevelDestination in TopLevelDestination.entries) {
                    NavigationBarItem(
                        selected = topLevelDestination.isSelected(mainNavController),
                        onClick = { mainNavController.navigateTo(topLevelDestination) },
                        icon = {
                            Icon(
                                if (topLevelDestination.isSelected(mainNavController)) topLevelDestination.selectedIcon
                                else topLevelDestination.unselectedIcon, contentDescription = null
                            )
                        },
                        label = { Text(topLevelDestination.label) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = rootNavController::navigateToNewItem) {
                Icon(Icons.Default.Add, contentDescription = "New item")
            }
        },
        content = { paddingValues -> content(paddingValues, mainNavController) },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    )
}
