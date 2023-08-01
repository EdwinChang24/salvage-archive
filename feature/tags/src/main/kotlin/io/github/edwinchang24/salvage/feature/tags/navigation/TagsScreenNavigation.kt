package io.github.edwinchang24.salvage.feature.tags.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.edwinchang24.salvage.feature.tags.TagsRoute

const val TagsScreenNavigationRoute = "tags"

fun NavGraphBuilder.tagsScreen() {
    composable(TagsScreenNavigationRoute) {
        TagsRoute()
    }
}
