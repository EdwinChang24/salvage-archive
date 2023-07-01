package io.github.edwinchang24.salvage.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.edwinchang24.salvage.feature.itemediting.edititem.navigateToEdit
import io.github.edwinchang24.salvage.feature.saved.navigation.SavedScreenNavigationRoute
import io.github.edwinchang24.salvage.feature.saved.navigation.savedScreen
import io.github.edwinchang24.salvage.ui.MainContent

const val MainContentRoute = "content"

fun NavGraphBuilder.mainContent(rootNavController: NavController) {
    composable(MainContentRoute) {
        val contentNavController = rememberNavController()
        MainContent(rootNavController = rootNavController) { contentPadding ->
            NavHost(
                navController = contentNavController,
                startDestination = SavedScreenNavigationRoute,
                modifier = Modifier.padding(contentPadding)
            ) {
                savedScreen(onEditItem = rootNavController::navigateToEdit)
            }
        }
    }
}
