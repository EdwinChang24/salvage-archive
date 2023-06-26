package io.github.edwinchang24.salvage.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.edwinchang24.salvage.feature.saved.navigation.SavedScreenNavigationRoute
import io.github.edwinchang24.salvage.feature.saved.navigation.savedScreen

@Composable
fun SalvageNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SavedScreenNavigationRoute,
        modifier = modifier
    ) {
        savedScreen()
    }
}
