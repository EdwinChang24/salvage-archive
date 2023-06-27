package io.github.edwinchang24.salvage.feature.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.edwinchang24.salvage.core.ui.bottomsheet.ItemSheet
import io.github.edwinchang24.salvage.core.ui.bottomsheet.LocalSalvageBottomSheetState
import io.github.edwinchang24.salvage.core.ui.items.ItemListUiState
import io.github.edwinchang24.salvage.core.ui.items.itemList
import kotlinx.coroutines.launch

@Composable
fun SavedScreen(modifier: Modifier = Modifier, viewModel: SavedScreenViewModel = hiltViewModel()) {
    val listState by viewModel.listState.collectAsStateWithLifecycle()
    val bottomSheetState = LocalSalvageBottomSheetState.current
    val scope = rememberCoroutineScope()
    if (listState is ItemListUiState.Success) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier.fillMaxSize()
        ) {
            itemList(listState, onItemLongClick = { item ->
                bottomSheetState.showBottomSheet {
                    ItemSheet(
                        item = item,
                        onDeleteItem = { viewModel.deleteItem(item) },
                        onDismissBottomSheet = {
                            scope.launch { bottomSheetState.hideBottomSheet() }
                        }
                    )
                }
            })
        }
    } else {
        Text(text = "Loading...", modifier = modifier)
    }
}
