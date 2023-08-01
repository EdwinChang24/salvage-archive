package io.github.edwinchang24.salvage.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.ui.bottomsheet.LocalSalvageBottomSheetState
import io.github.edwinchang24.salvage.core.ui.bottomsheet.SalvageBottomSheetState
import io.github.edwinchang24.salvage.navigation.SalvageNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val bottomSheetState =
        SalvageBottomSheetState(rememberModalBottomSheetState(), rememberCoroutineScope())
    CompositionLocalProvider(LocalSalvageBottomSheetState provides bottomSheetState) {
        SalvageTheme {
            val navController = rememberNavController()
            MainContent(navController) { contentPadding ->
                SalvageNavHost(navController, modifier = Modifier.padding(contentPadding))
            }
            if (bottomSheetState.bottomSheetShown) {
                ModalBottomSheet(
                    onDismissRequest = { bottomSheetState.bottomSheetShown = false },
                    sheetState = bottomSheetState.sheetState,
                    windowInsets = WindowInsets(0, 0, 0, 0)
                ) {
                    with(bottomSheetState) {
                        bottomSheetContent(Modifier.windowInsetsPadding(WindowInsets.navigationBars))
                    }
                }
            }
        }
    }
}
