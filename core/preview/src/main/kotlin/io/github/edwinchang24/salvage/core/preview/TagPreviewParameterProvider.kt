package io.github.edwinchang24.salvage.core.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.github.edwinchang24.salvage.core.model.Tag

class TagPreviewParameterProvider : PreviewParameterProvider<List<Tag>> {
    override val values: Sequence<List<Tag>> get() = sequenceOf(tags)
}

private val tags = listOf(
    Tag(
        id = "1",
        name = "Read later",
        color = 0xffd700,
        description = null
    ),
    Tag(
        id = "2",
        name = "Tech",
        color = 0xcc0000,
        description = "The latest trends, gadgets, and advancements in the tech world"
    ),
    Tag(
        id = "3",
        name = "Science/Nature",
        color = 0x00ff00,
        description = "Discover the wonders of science and the natural world"
    ),
    Tag(
        id = "4",
        name = "Arts/Culture",
        color = 0x0000ff,
        description = "Articles that celebrate creativity and artistic expressions"
    ),
    Tag(
        id = "5",
        name = "Cooking",
        color = 0xffff00,
        description = "Delicious recipes, cooking techniques, food reviews, and culinary explorations"
    ),
    Tag(
        id = "6",
        name = "Politics",
        color = 0xff8000,
        description = "Stay informed about current affairs, political developments, and social justice issues"
    ),
    Tag(
        id = "7",
        name = "Sports",
        color = 0x800080,
        description = "Sporting events, fitness routines, athlete profiles, and workout tips"
    ),
    Tag(
        id = "8",
        name = "Entertainment",
        color = 0xffc0cb,
        description = "Stay entertained with articles on movies, TV shows, celebrity news, gaming, and memes"
    )
)
