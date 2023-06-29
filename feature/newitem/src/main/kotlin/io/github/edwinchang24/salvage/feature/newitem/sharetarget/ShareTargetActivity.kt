package io.github.edwinchang24.salvage.feature.newitem.sharetarget

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.feature.newitem.ui.NewItemScreen
import io.github.edwinchang24.salvage.feature.newitem.ui.NewItemScreenViewModel

@AndroidEntryPoint
class ShareTargetActivity : ComponentActivity() {
    private val newItemScreenViewModel: NewItemScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        newItemScreenViewModel.sharedUrl = intent.extras?.getString(Intent.EXTRA_TEXT)
        setContent {
            SalvageTheme {
                NewItemScreen(onFinish = { finish() }, viewModel = newItemScreenViewModel)
            }
        }
    }
}
