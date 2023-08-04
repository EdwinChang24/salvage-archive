package io.github.edwinchang24.salvage.feature.tags

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.menu.TagMenuContent
import io.github.edwinchang24.salvage.core.model.Tag
import io.github.edwinchang24.salvage.core.preview.TagPreviewParameterProvider
import io.github.edwinchang24.salvage.core.preview.annotations.AllPreviews
import io.github.edwinchang24.salvage.core.ui.bottomsheet.BottomSheetContent
import io.github.edwinchang24.salvage.core.ui.bottomsheet.LocalSalvageBottomSheetState
import io.github.edwinchang24.salvage.core.ui.tags.TagListUiState
import io.github.edwinchang24.salvage.core.ui.tags.tagList

@Composable
fun TagsRoute(
    modifier: Modifier = Modifier,
    onEditTag: (tagId: String) -> Unit = {},
    viewModel: TagsScreenViewModel = hiltViewModel()
) = TagsScreen(
    listState = viewModel.listState.collectAsStateWithLifecycle().value,
    modifier = modifier,
    onEditTag = onEditTag,
    onDeleteTag = viewModel::deleteTag
)

@Composable
fun TagsScreen(
    listState: TagListUiState,
    modifier: Modifier = Modifier,
    onEditTag: (tagId: String) -> Unit = {},
    onDeleteTag: (tagId: String) -> Unit = {}
) {
    val bottomSheetState = LocalSalvageBottomSheetState.current
    if (listState is TagListUiState.Success) {
        LazyVerticalGrid(columns = GridCells.Adaptive(300.dp), modifier = modifier.fillMaxSize()) {
            tagList(
                state = listState,
                onTagClick = {},
                onTagLongClick = { tagId ->
                    bottomSheetState.showBottomSheet { modifier ->
                        BottomSheetContent(
                            menuContent = TagMenuContent(
                                onEditTag = { onEditTag(tagId) },
                                onDeleteTag = { onDeleteTag(tagId) }
                            ),
                            modifier = modifier
                        )
                    }
                }
            )
        }
    } else {
        Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
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
