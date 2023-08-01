package io.github.edwinchang24.salvage.feature.tags

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.model.Tag
import io.github.edwinchang24.salvage.core.preview.TagPreviewParameterProvider
import io.github.edwinchang24.salvage.core.preview.annotations.AllPreviews
import io.github.edwinchang24.salvage.core.ui.tags.TagListUiState
import io.github.edwinchang24.salvage.core.ui.tags.tagList

@Composable
fun TagsRoute(modifier: Modifier = Modifier, viewModel: TagsScreenViewModel = hiltViewModel()) {
    TagsScreen(listState = viewModel.listState.collectAsStateWithLifecycle().value, modifier = modifier)
}

@Composable
fun TagsScreen(listState: TagListUiState, modifier: Modifier = Modifier) {
    if (listState is TagListUiState.Success) {
        LazyVerticalGrid(columns = GridCells.Adaptive(300.dp), modifier = modifier.fillMaxSize()) {
            tagList(state = listState, onTagClick = {}, onTagLongClick = {})
        }
    } else {
        Text("Loading...", modifier = modifier)
    }
}

@AllPreviews
@Composable
private fun TagsScreenPreview(@PreviewParameter(TagPreviewParameterProvider::class) tags: List<Tag>) = SalvageTheme {
    Scaffold { contentPadding ->
        TagsScreen(listState = TagListUiState.Success(tags), modifier = Modifier.padding(contentPadding))
    }
}

@AllPreviews
@Composable
private fun TagsScreenLoadingPreview() = SalvageTheme {
    Scaffold { contentPadding ->
        TagsScreen(listState = TagListUiState.Loading, modifier = Modifier.padding(contentPadding))
    }
}
