package io.github.edwinchang24.salvage.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.edwinchang24.salvage.feature.itemediting.edititem.editItemScreen
import io.github.edwinchang24.salvage.feature.itemediting.newitem.newItemScreen

@Composable
fun SalvageNavHost(modifier: Modifier = Modifier) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = MainContentRoute,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        mainContent(rootNavController = rootNavController)
        newItemScreen(onFinish = rootNavController::popBackStack)
        editItemScreen(onFinish = rootNavController::popBackStack)
    }
}
