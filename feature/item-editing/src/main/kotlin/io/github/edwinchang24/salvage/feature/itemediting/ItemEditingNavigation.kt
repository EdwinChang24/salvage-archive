package io.github.edwinchang24.salvage.feature.itemediting

import android.content.Context
import android.content.Intent

fun Context.startEditItemActivity(itemId: String) = startActivity(
    Intent()
        .setClass(this, ItemEditingActivity::class.java)
        .putExtra(ExistingItemId, itemId)
)

fun Context.startNewItemActivity() = startActivity(
    Intent().setClass(this, ItemEditingActivity::class.java)
)
