package io.github.edwinchang24.salvage.feature.saved

import androidx.annotation.ColorInt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.menu.ItemMenuContent
import io.github.edwinchang24.salvage.core.model.Item
import io.github.edwinchang24.salvage.core.preview.ItemPreviewParameterProvider
import io.github.edwinchang24.salvage.core.preview.annotations.AllPreviews
import io.github.edwinchang24.salvage.core.ui.bottomsheet.BottomSheetContent
import io.github.edwinchang24.salvage.core.ui.bottomsheet.LocalSalvageBottomSheetState
import io.github.edwinchang24.salvage.core.ui.items.ItemListUiState
import io.github.edwinchang24.salvage.core.ui.items.itemList
import io.github.edwinchang24.salvage.core.util.launchCustomTab

@Composable
fun SavedRoute(
    onEditItem: (itemId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SavedScreenViewModel = hiltViewModel()
) {
    val listState by viewModel.listState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    @ColorInt val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
    val bottomSheetState = LocalSalvageBottomSheetState.current
    SavedScreen(
        onItemClick = { item -> launchCustomTab(context, url = item.url, barColor = backgroundColor) },
        onItemLongClick = { item ->
            bottomSheetState.showBottomSheet { modifier ->
                BottomSheetContent(
                    ItemMenuContent(
                        context,
                        item,
                        onEditItem = { onEditItem(item.id) },
                        onDeleteItem = { viewModel.deleteItem(item) },
                        backgroundColor
                    ),
                    modifier = modifier
                )
            }
        }, modifier = modifier,
        listState = listState
    )
}

@Composable
fun SavedScreen(
    listState: ItemListUiState,
    onItemClick: (item: Item) -> Unit,
    onItemLongClick: (item: Item) -> Unit,
    modifier: Modifier = Modifier
) {
    if (listState is ItemListUiState.Success) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier.fillMaxSize()
        ) {
            itemList(listState, onItemClick = onItemClick, onItemLongClick = onItemLongClick)
        }
    } else {
        Text(text = "Loading...", modifier = modifier)
    }
}

@AllPreviews
@Composable
private fun SavedScreenPreview(@PreviewParameter(ItemPreviewParameterProvider::class) items: List<Item>) =
    SalvageTheme {
        Scaffold { contentPadding ->
            SavedScreen(
                listState = ItemListUiState.Success(items),
                onItemClick = {},
                onItemLongClick = {},
                modifier = Modifier.padding(contentPadding)
            )
        }
    }

@AllPreviews
@Composable
private fun SavedScreenLoadingPreview() = SalvageTheme {
    Scaffold { contentPadding ->
        SavedScreen(
            listState = ItemListUiState.Loading,
            onItemClick = {},
            onItemLongClick = {},
            modifier = Modifier.padding(contentPadding)
        )
    }
}
