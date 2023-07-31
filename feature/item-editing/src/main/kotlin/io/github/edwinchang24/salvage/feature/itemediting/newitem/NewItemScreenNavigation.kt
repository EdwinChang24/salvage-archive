package io.github.edwinchang24.salvage.feature.itemediting.newitem

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import io.github.edwinchang24.salvage.feature.itemediting.ui.ItemEditingRoute

const val NewItemScreenNavigationRoute = "newitem"

fun NavController.navigateToNewItem() = navigate(NewItemScreenNavigationRoute)

fun NavGraphBuilder.newItemScreen(onFinish: () -> Unit) {
    dialog(
        NewItemScreenNavigationRoute,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        ItemEditingRoute(onFinish = onFinish)
    }
}
