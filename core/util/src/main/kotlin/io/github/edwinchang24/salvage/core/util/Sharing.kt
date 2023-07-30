package io.github.edwinchang24.salvage.core.util

import android.content.Context
import android.content.Intent

fun Context.shareUrl(url: String, title: String?) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, url)
        if (title != null) putExtra(Intent.EXTRA_TITLE, title)
    }
    startActivity(Intent.createChooser(intent, null))
}
