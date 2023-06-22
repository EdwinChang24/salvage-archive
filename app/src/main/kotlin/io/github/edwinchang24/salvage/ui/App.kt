package io.github.edwinchang24.salvage.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.edwinchang24.salvage.navigation.SalvageNavHost

@Composable
fun App(modifier: Modifier = Modifier) {
    SalvageNavHost(modifier = modifier)
}
