package io.github.edwinchang24.salvage.feature.itemediting.edititem

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.edwinchang24.salvage.feature.itemediting.ui.ItemEditingScreen

const val EditItemScreenNavigationRoute = "edititem"
const val ItemId = "itemId"

fun NavController.navigateToEdit(itemId: String) = navigate("$EditItemScreenNavigationRoute/$itemId")

fun NavGraphBuilder.editItemScreen(onFinish: () -> Unit) {
    composable(
        route = "$EditItemScreenNavigationRoute/{$ItemId}",
        arguments = listOf(navArgument(ItemId) { type = NavType.StringType })
    ) {
        ItemEditingScreen(onFinish = onFinish)
    }
}
