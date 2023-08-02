package io.github.edwinchang24.salvage.feature.tagediting

import android.content.Context
import android.content.Intent

fun Context.startEditTagActivity(tagId: String) = startActivity(
    Intent()
        .setClass(this, TagEditingActivity::class.java)
        .putExtra(ExistingTagId, tagId)
)

fun Context.startNewTagActivity() = startActivity(
    Intent().setClass(this, TagEditingActivity::class.java)
)
