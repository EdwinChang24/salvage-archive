package io.github.edwinchang24.salvage.core.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.datetime.Instant

class ItemPreviewParameterProvider : PreviewParameterProvider<List<Item>> {
    override val values: Sequence<List<Item>> get() = sequenceOf(items)
}

private val items = listOf(
    Item(
        id = "1",
        name = "Stephen Hawking: Visionary physicist dies aged 76",
        url = "https://www.bbc.com/news/uk-43396008",
        description = "World renowned physicist Stephen Hawking has died at the age of 76.",
        timeAdded = Instant.fromEpochSeconds(1521086400),
        timePublished = Instant.fromEpochSeconds(1521000000)
    ),
    Item(
        id = "2",
        name = "A Message to Our Customers",
        url = "https://www.apple.com/customer-letter/",
        description = """
            The United States government has demanded that Apple take an unprecedented step which threatens the 
            security of our customers. We oppose this order, which has implications far beyond the legal case at hand.
            """.trimIndent(),
        timeAdded = Instant.fromEpochSeconds(1455685200),
        timePublished = Instant.fromEpochSeconds(1455598800)
    ),
    Item(
        id = "3",
        name = "Mechanical Watch",
        url = "https://ciechanow.ski/mechanical-watch/",
        description = """
            In the world of modern portable devices, it may be hard to believe that merely a few decades ago the most 
            convenient way to keep track of time was a mechanical watch. Unlike their quartz and smart siblings, 
            mechanical watches can run without using any batteries or other electronic components.
        """.trimIndent(),
        timeAdded = Instant.fromEpochSeconds(1651723200),
        timePublished = Instant.fromEpochSeconds(1651636800)
    ),
    Item(
        id = "4",
        name = "Remembering Steve",
        url = "https://www.apple.com/stevejobs/",
        description = """
            Over a million people from all over the world have shared their memories, thoughts, and feelings about 
            Steve. One thing they all have in common — from personal friends to colleagues to owners of Apple products 
            — is how they’ve been touched by his passion and creativity.
        """.trimIndent(),
        timeAdded = Instant.fromEpochSeconds(1317873600),
        timePublished = Instant.fromEpochSeconds(1317787200)
    ),
    Item(
        id = "5",
        name = "YouTube-dl has received a DMCA takedown from RIAA",
        url = "https://github.com/github/dmca/blob/master/2020/10/2020-10-23-RIAA.md",
        description = """
            The clear purpose of this source code is to (i) circumvent the technological protection measures used by 
            authorized streaming services such as YouTube, and (ii) reproduce and distribute music videos and sound 
            recordings owned by our member companies without authorization for such use. We note that the source code 
            is described on GitHub as “a command-line program to download videos from YouTube.com and a few more sites.”
        """.trimIndent(),
        timeAdded = Instant.fromEpochSeconds(1603512000),
        timePublished = Instant.fromEpochSeconds(1603425600)
    )
)
