package io.github.edwinchang24.salvage.core.ui.tags

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.model.Tag
import io.github.edwinchang24.salvage.core.preview.TagPreviewParameterProvider
import io.github.edwinchang24.salvage.core.util.adjusted

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TagPill(tag: Tag, modifier: Modifier = Modifier, onClick: () -> Unit = {}, onLongClick: () -> Unit = {}) {
    val color by animateColorAsState(Color(tag.color).adjusted(), label = "color")
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(color)
            .animateContentSize()
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
    ) {
        Text(tag.name, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun TagPillPreview(@PreviewParameter(TagPreviewParameterProvider::class) tagList: List<Tag>) = SalvageTheme {
    Surface {
        TagPill(tagList.first(), modifier = Modifier.padding(24.dp))
    }
}
