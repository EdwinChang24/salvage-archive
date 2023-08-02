package io.github.edwinchang24.salvage.feature.tagediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.edwinchang24.salvage.core.design.SalvageTheme

@AndroidEntryPoint
class TagEditingActivity : ComponentActivity() {
    private val tagEditingScreenViewModel: TagEditingScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val existingTagId = intent.extras?.getString(ExistingTagId)
        if (existingTagId != null) tagEditingScreenViewModel.setExistingTagId(existingTagId)
        setContent {
            SalvageTheme {
                TagEditingRoute(onFinish = ::finish)
            }
        }
    }
}
