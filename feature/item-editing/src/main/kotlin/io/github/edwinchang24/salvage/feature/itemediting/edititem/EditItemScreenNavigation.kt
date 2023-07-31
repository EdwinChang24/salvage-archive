package io.github.edwinchang24.salvage.feature.itemediting.edititem

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import io.github.edwinchang24.salvage.feature.itemediting.ui.ItemEditingRoute

const val EditItemScreenNavigationRoute = "edititem"
const val ItemId = "itemId"

fun NavController.navigateToEdit(itemId: String) = navigate("$EditItemScreenNavigationRoute/$itemId")

fun NavGraphBuilder.editItemScreen(onFinish: () -> Unit) {
    dialog(
        route = "$EditItemScreenNavigationRoute/{$ItemId}",
        arguments = listOf(navArgument(ItemId) { type = NavType.StringType }),
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        ItemEditingRoute(onFinish = onFinish)
    }
}
