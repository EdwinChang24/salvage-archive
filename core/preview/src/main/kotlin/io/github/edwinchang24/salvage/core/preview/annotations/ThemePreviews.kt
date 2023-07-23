package io.github.edwinchang24.salvage.core.preview.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers

const val Light = "Themes (Light)"
const val Dark = "Themes (Dark)"

@Preview(name = "Default", group = Light, wallpaper = Wallpapers.NONE)
@Preview(name = "Blue", group = Light, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Preview(name = "Red", group = Light, wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE)
@Preview(name = "Green", group = Light, wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE)
annotation class LightThemePreviews

@Preview(name = "Default", group = Dark, wallpaper = Wallpapers.NONE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(
    name = "Blue",
    group = Dark,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Red",
    group = Dark,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Green",
    group = Dark,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class DarkThemePreviews

@LightThemePreviews
@DarkThemePreviews
annotation class ThemePreviews
