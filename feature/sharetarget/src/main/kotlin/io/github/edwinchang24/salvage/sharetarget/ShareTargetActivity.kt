package io.github.edwinchang24.salvage.sharetarget

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.github.edwinchang24.salvage.core.model.Item
import kotlinx.datetime.Clock
import java.util.UUID

@AndroidEntryPoint
class ShareTargetActivity : ComponentActivity() {
    private val viewModel: ShareTargetActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedUrl = intent.extras?.getString(Intent.EXTRA_TEXT)
        if (sharedUrl != null) viewModel.onUrlChanged(sharedUrl)
        setContent {
            val url by viewModel.url.collectAsStateWithLifecycle()
            Column {
                Text("This is the share target")
                TextField(value = url, onValueChange = viewModel::onUrlChanged)
                Button(onClick = {
                    viewModel.addItem(
                        Item(
                            id = UUID.randomUUID().toString(),
                            name = null,
                            url = url,
                            description = null,
                            timeAdded = Clock.System.now(),
                            timePublished = null
                        )
                    )
                    finish()
                }) {
                    Text("Done")
                }
            }
        }
    }
}
