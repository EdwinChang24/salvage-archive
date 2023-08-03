package io.github.edwinchang24.salvage.feature.itemediting

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.edwinchang24.salvage.core.design.SalvageTheme

const val ExistingItemId = "existingItemId"

@AndroidEntryPoint
class ItemEditingActivity : ComponentActivity() {
    private val itemEditingScreenViewModel: ItemEditingScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (savedInstanceState == null) {
            val existingItemId = intent.extras?.getString(ExistingItemId)
            if (existingItemId != null) itemEditingScreenViewModel.setExistingItemId(existingItemId)
            val sharedUrl = intent.extras?.getString(Intent.EXTRA_TEXT)
            if (sharedUrl != null) itemEditingScreenViewModel.onEditUrl(sharedUrl)
        }
        setContent {
            SalvageTheme {
                ItemEditingRoute(onFinish = { finish() }, viewModel = itemEditingScreenViewModel)
            }
        }
    }
}
