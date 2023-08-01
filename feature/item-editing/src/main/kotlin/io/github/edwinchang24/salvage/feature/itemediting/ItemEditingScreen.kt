package io.github.edwinchang24.salvage.feature.itemediting

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.preview.annotations.AllPreviews
import io.github.edwinchang24.salvage.core.util.GetterSetter
import io.github.edwinchang24.salvage.core.util.unaryPlus

@Composable
fun ItemEditingRoute(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemEditingScreenViewModel = hiltViewModel()
) = ItemEditingScreen(
    editingItem = viewModel.existingItemId.collectAsStateWithLifecycle().value != null,
    name = GetterSetter(viewModel.name.collectAsStateWithLifecycle().value, viewModel::onEditName),
    url = GetterSetter(viewModel.url.collectAsStateWithLifecycle().value, viewModel::onEditUrl),
    description = GetterSetter(
        viewModel.description.collectAsStateWithLifecycle().value,
        viewModel::onEditDescription
    ),
    onSubmitItem = viewModel::submitItem,
    onFinish = onFinish,
    modifier = modifier
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditingScreen(
    editingItem: Boolean,
    name: GetterSetter<String>,
    url: GetterSetter<String>,
    description: GetterSetter<String>,
    onSubmitItem: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (editingItem) "Edit Item" else "New Item") },
                navigationIcon = {
                    IconButton(onClick = onFinish) { Icon(Icons.Default.ArrowBack, contentDescription = "Navigate up") }
                }
            )
        },
        bottomBar = {
            BottomBar(
                url = url(),
                onDone = {
                    onSubmitItem()
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
            NameField(name = name)
            UrlField(url = url)
            DescriptionField(description = description)
        }
    }
}

@Composable
private fun NameField(name: GetterSetter<String>) {
    OutlinedTextField(
        value = name(),
        onValueChange = name.setValue,
        label = { Text("Name") },
        trailingIcon = {
            if (name() != "") {
                IconButton(onClick = { name.setValue("") }) {
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
private fun UrlField(url: GetterSetter<String>) {
    OutlinedTextField(
        value = url(),
        onValueChange = url.setValue,
        label = { Text("URL*") },
        leadingIcon = { Icon(Icons.Default.Link, contentDescription = null) },
        supportingText = { Text("*required") },
        isError = url() == "",
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Uri,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DescriptionField(description: GetterSetter<String>) {
    OutlinedTextField(
        value = description(),
        onValueChange = description.setValue,
        label = { Text("Description") },
        trailingIcon = {
            if (description.value != "") {
                IconButton(onClick = { description.setValue("") }) {
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

@AllPreviews
@Composable
private fun ItemEditingScreenPreview() = SalvageTheme {
    ItemEditingScreen(
        editingItem = true,
        name = +remember { mutableStateOf("") },
        url = +remember { mutableStateOf("") },
        description = +remember { mutableStateOf("") },
        onSubmitItem = {},
        onFinish = {}
    )
}
