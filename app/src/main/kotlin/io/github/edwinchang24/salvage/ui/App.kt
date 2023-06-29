package io.github.edwinchang24.salvage.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.ui.bottomsheet.LocalSalvageBottomSheetState
import io.github.edwinchang24.salvage.core.ui.bottomsheet.SalvageBottomSheetState
import io.github.edwinchang24.salvage.navigation.SalvageNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val bottomSheetState = SalvageBottomSheetState(rememberModalBottomSheetState())
    CompositionLocalProvider(LocalSalvageBottomSheetState provides bottomSheetState) {
        SalvageTheme {
            SalvageNavHost()
            if (bottomSheetState.bottomSheetShown) {
                ModalBottomSheet(
                    onDismissRequest = { bottomSheetState.bottomSheetShown = false },
                    sheetState = bottomSheetState.sheetState,
                    windowInsets = WindowInsets(0, 0, 0, 0)
                ) {
                    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)) {
                        with(bottomSheetState) { bottomSheetContent() }
                    }
                }
            }
        }
    }
}
