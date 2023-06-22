package io.github.edwinchang24.salvage.saved.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.edwinchang24.salvage.saved.SavedScreen

const val SavedScreenNavigationRoute = "saved"

fun NavGraphBuilder.savedScreen() {
    composable(SavedScreenNavigationRoute) {
        SavedScreen()
    }
}
