package io.github.edwinchang24.salvage.core.ui.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SalvageBottomSheetState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val sheetState: SheetState,
    private val coroutineScope: CoroutineScope
) {

    var bottomSheetShown by mutableStateOf(false)

    var bottomSheetContent: @Composable (Modifier) -> Unit = {}
        private set

    fun showBottomSheet(content: @Composable (Modifier) -> Unit) {
        bottomSheetContent = content
        bottomSheetShown = true
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun hideBottomSheet() {
        coroutineScope.launch(Dispatchers.Default) {
            sheetState.hide()
            bottomSheetShown = false
        }
    }
}

val LocalSalvageBottomSheetState: ProvidableCompositionLocal<SalvageBottomSheetState> =
    compositionLocalOf { error("Bottom sheet state not provided") }
