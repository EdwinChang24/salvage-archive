package io.github.edwinchang24.salvage.core.design

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SalvageTheme(content: @Composable () -> Unit) {
    val useDarkTheme = isSystemInDarkTheme()
    val useDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = if (useDynamicColor) {
        if (useDarkTheme) dynamicDarkColorScheme(LocalContext.current)
        else dynamicLightColorScheme(LocalContext.current)
    } else {
        if (useDarkTheme) darkColorScheme() else lightColorScheme()
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        WindowCompat.getInsetsController((view.context as Activity).window, view).isAppearanceLightStatusBars =
            !useDarkTheme
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}
