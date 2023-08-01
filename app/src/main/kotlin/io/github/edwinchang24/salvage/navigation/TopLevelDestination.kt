package io.github.edwinchang24.salvage.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import io.github.edwinchang24.salvage.feature.itemediting.startNewItemActivity
import io.github.edwinchang24.salvage.feature.saved.navigation.SavedScreenNavigationRoute
import io.github.edwinchang24.salvage.feature.tags.navigation.TagsScreenNavigationRoute

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
    val route: String,
    val fabContent: @Composable ((Context) -> Unit)? = null
) {
    SAVED(
        selectedIcon = Icons.Filled.Bookmarks,
        unselectedIcon = Icons.Outlined.Bookmarks,
        label = "Saved",
        route = SavedScreenNavigationRoute,
        fabContent = { context ->
            FloatingActionButton(onClick = context::startNewItemActivity) {
                Icon(Icons.Default.Add, contentDescription = "New item")
            }
        }
    ),
    TAGS(
        selectedIcon = Icons.Filled.Tag,
        unselectedIcon = Icons.Outlined.Tag,
        label = "Tags",
        route = TagsScreenNavigationRoute,
        fabContent = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "New tag")
            }
        }
    )
}

fun NavHostController.navigateTo(topLevelDestination: TopLevelDestination) {
    navigate(
        topLevelDestination.route,
        navOptions = navOptions {
            popUpTo(graph.findStartDestination().id) {
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    )
}

@Composable
fun NavHostController.selectedTopLevelDestination() =
    currentBackStackEntryAsState().value?.destination?.route?.let { route ->
        TopLevelDestination.entries.firstOrNull { it.route == route }
    }

@Composable
fun TopLevelDestination.isInHierarchy(navController: NavHostController) =
    navController.currentBackStackEntry?.destination?.hierarchy?.any { it.route == route } ?: false
