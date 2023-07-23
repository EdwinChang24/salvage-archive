package io.github.edwinchang24.salvage.core.util

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState

@Immutable
data class GetterSetter<T>(val value: T, val setValue: (value: T) -> Unit) {
    operator fun invoke() = value
}

operator fun <T> MutableState<T>.unaryPlus(): GetterSetter<T> {
    val (value, setValue) = this
    return GetterSetter(value, setValue)
}
