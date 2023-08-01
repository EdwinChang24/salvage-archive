package io.github.edwinchang24.salvage.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.edwinchang24.salvage.feature.itemediting.startEditItemActivity
import io.github.edwinchang24.salvage.feature.saved.navigation.SavedScreenNavigationRoute
import io.github.edwinchang24.salvage.feature.saved.navigation.savedScreen
import io.github.edwinchang24.salvage.feature.tags.navigation.tagsScreen

@Composable
fun SalvageNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = SavedScreenNavigationRoute,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        savedScreen(onEditItem = context::startEditItemActivity)
        tagsScreen()
    }
}
