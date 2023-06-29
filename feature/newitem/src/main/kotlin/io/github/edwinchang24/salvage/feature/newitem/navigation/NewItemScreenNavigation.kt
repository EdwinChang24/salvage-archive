package io.github.edwinchang24.salvage.feature.newitem.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.edwinchang24.salvage.feature.newitem.ui.NewItemScreen

const val NewItemScreenNavigationRoute = "newitem"

fun NavGraphBuilder.newItemScreen(onFinish: () -> Unit) {
    composable(NewItemScreenNavigationRoute) {
        NewItemScreen(onFinish = onFinish)
    }
}
