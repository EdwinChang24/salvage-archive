package io.github.edwinchang24.salvage.core.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

fun launchCustomTab(context: Context, url: String, @ColorInt barColor: Int) {
    val customTabBarColor = CustomTabColorSchemeParams.Builder().setToolbarColor(barColor).build()
    val customTabsIntent = CustomTabsIntent.Builder().setDefaultColorSchemeParams(customTabBarColor).build()
    customTabsIntent.launchUrl(context, url.toUri())
}
