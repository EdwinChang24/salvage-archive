package io.github.edwinchang24.salvage.feature.newitem.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemScreen(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewItemScreenViewModel = hiltViewModel()
) {
    val (name, onEditName) = remember { mutableStateOf("") }
    val (url, onEditUrl) = remember { mutableStateOf("") }
    val (description, onEditDescription) = remember { mutableStateOf("") }
    LaunchedEffect(true) { if (viewModel.sharedUrl != null) onEditUrl(viewModel.sharedUrl ?: "") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Item") },
                navigationIcon = {
                    IconButton(onClick = onFinish) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Navigate up")
                    }
                }
            )
        },
        bottomBar = { BottomBar(onFinish = onFinish, onDone = { viewModel.addItem(name, url, description) }) },
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
            NameField(name = name, onEditName = onEditName)
            UrlField(url = url, onEditUrl = onEditUrl)
            DescriptionField(description, onEditDescription)
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
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DescriptionField(
    description: String,
    onEditDescription: (String) -> Unit
) {
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
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun BottomBar(onFinish: () -> Unit, onDone: () -> Unit) {
    BottomAppBar(contentPadding = PaddingValues(horizontal = 24.dp)) {
        TextButton(onClick = onFinish, modifier = Modifier.weight(1f)) {
            Text("Cancel")
        }
        Spacer(modifier = Modifier.width(24.dp))
        Button(
            onClick = {
                onDone()
                onFinish()
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Default.Done, contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Done")
        }
    }
}
