package io.github.edwinchang24.salvage.feature.itemediting.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditingScreen(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemEditingScreenViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val url by viewModel.url.collectAsStateWithLifecycle()
    val description by viewModel.description.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (viewModel.existingItemId == null) "New Item" else "Edit Item") },
                navigationIcon = {
                    IconButton(onClick = onFinish) { Icon(Icons.Default.ArrowBack, contentDescription = "Navigate up") }
                }
            )
        },
        bottomBar = {
            BottomBar(
                url = url,
                onDone = {
                    viewModel.submitItem()
                    onFinish()
                },
                onCancel = onFinish
            )
        },
        modifier = modifier
    ) { contentPadding ->
        val scrollState = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier
                .padding(contentPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            NameField(name = name, onEditName = viewModel::onEditName)
            UrlField(url = url, onEditUrl = viewModel::onEditUrl)
            DescriptionField(description = description, onEditDescription = viewModel::onEditDescription)
        }
    }
}

@Composable
private fun NameField(name: String, onEditName: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onEditName,
        label = { Text("Name") },
        trailingIcon = {
            if (name != "") {
                IconButton(onClick = { onEditName("") }) {
                    Icon(Icons.Default.Delete, contentDescription = "Clear name")
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun UrlField(url: String, onEditUrl: (String) -> Unit) {
    OutlinedTextField(
        value = url,
        onValueChange = onEditUrl,
        label = { Text("URL*") },
        leadingIcon = { Icon(Icons.Default.Link, contentDescription = null) },
        supportingText = { Text("*required") },
        isError = url == "",
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Uri,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DescriptionField(description: String, onEditDescription: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = onEditDescription,
        label = { Text("Description") },
        trailingIcon = {
            if (description != "") {
                IconButton(onClick = { onEditDescription("") }) {
                    Icon(Icons.Default.Delete, contentDescription = "Clear description")
                }
            }
        },
        minLines = 3,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun BottomBar(url: String, onDone: () -> Unit, onCancel: () -> Unit) {
    BottomAppBar(contentPadding = PaddingValues(horizontal = 24.dp)) {
        TextButton(onClick = onCancel, modifier = Modifier.weight(1f)) { Text("Cancel") }
        Spacer(modifier = Modifier.width(24.dp))
        Button(onClick = { if (url != "") onDone() }, enabled = url != "", modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Done, contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Done")
        }
    }
}
