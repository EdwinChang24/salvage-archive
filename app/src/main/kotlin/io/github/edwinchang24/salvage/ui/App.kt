package io.github.edwinchang24.salvage.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.navigation.SalvageNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    SalvageTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Salvage") }) },
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { contentPadding ->
            SalvageNavHost(modifier = modifier.padding(contentPadding))
        }
    }
}
