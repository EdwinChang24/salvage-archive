package io.github.edwinchang24.salvage.feature.saved.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.edwinchang24.salvage.feature.saved.SavedRoute

const val SavedScreenNavigationRoute = "saved"

fun NavGraphBuilder.savedScreen(onEditItem: (itemId: String) -> Unit) {
    composable(SavedScreenNavigationRoute) {
        SavedRoute(onEditItem = onEditItem)
    }
}
