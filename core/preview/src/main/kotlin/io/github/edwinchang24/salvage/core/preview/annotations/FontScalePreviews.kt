package io.github.edwinchang24.salvage.core.preview.annotations

import androidx.compose.ui.tooling.preview.Preview

const val FontScales = "Font Scales"

@Preview(name = "Tiny Font", group = FontScales, fontScale = 0.5f)
@Preview(name = "Small Font", group = FontScales, fontScale = 0.75f)
@Preview(name = "Regular Font", group = FontScales)
@Preview(name = "Large Font", group = FontScales, fontScale = 1.5f)
@Preview(name = "Extra Large Font", group = FontScales, fontScale = 2f)
annotation class FontScalePreviews
