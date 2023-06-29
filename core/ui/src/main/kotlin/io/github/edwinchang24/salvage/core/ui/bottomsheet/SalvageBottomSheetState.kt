package io.github.edwinchang24.salvage.core.ui.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SalvageBottomSheetState @OptIn(ExperimentalMaterial3Api::class) constructor(val sheetState: SheetState) {

    var bottomSheetShown by mutableStateOf(false)

    var bottomSheetContent: @Composable () -> Unit = {}
        private set

    fun showBottomSheet(content: @Composable () -> Unit) {
        bottomSheetContent = content
        bottomSheetShown = true
    }

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun hideBottomSheet() {
        coroutineScope {
            launch {
                sheetState.hide()
                bottomSheetShown = false
            }
        }
    }
}

val LocalSalvageBottomSheetState: ProvidableCompositionLocal<SalvageBottomSheetState> =
    compositionLocalOf { error("Bottom sheet state not provided") }
