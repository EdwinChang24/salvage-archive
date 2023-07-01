package io.github.edwinchang24.salvage.feature.itemediting.newitem

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.feature.itemediting.ui.ItemEditingScreen
import io.github.edwinchang24.salvage.feature.itemediting.ui.ItemEditingScreenViewModel

@AndroidEntryPoint
class ShareTargetActivity : ComponentActivity() {

    private val itemEditingScreenViewModel: ItemEditingScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val sharedUrl = intent.extras?.getString(Intent.EXTRA_TEXT)
        if (sharedUrl != null) itemEditingScreenViewModel.onEditUrl(sharedUrl)
        setContent {
            SalvageTheme {
                ItemEditingScreen(onFinish = { finish() }, viewModel = itemEditingScreenViewModel)
            }
        }
    }
}
