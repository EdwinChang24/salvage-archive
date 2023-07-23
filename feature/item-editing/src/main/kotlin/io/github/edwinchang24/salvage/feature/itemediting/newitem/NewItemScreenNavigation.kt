package io.github.edwinchang24.salvage.feature.itemediting.newitem

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.edwinchang24.salvage.feature.itemediting.ui.ItemEditingRoute

const val NewItemScreenNavigationRoute = "newitem"

fun NavController.navigateToNewItem() = navigate(NewItemScreenNavigationRoute)

fun NavGraphBuilder.newItemScreen(onFinish: () -> Unit) {
    composable(NewItemScreenNavigationRoute) {
        ItemEditingRoute(onFinish = onFinish)
    }
}
