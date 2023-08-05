package io.github.edwinchang24.salvage.feature.tagediting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.edwinchang24.salvage.core.design.SalvageTheme
import io.github.edwinchang24.salvage.core.model.Tag
import io.github.edwinchang24.salvage.core.preview.annotations.AllPreviews
import io.github.edwinchang24.salvage.core.ui.tags.TagColor
import io.github.edwinchang24.salvage.core.ui.tags.TagPill
import io.github.edwinchang24.salvage.core.util.GetterSetter
import io.github.edwinchang24.salvage.core.util.adjusted
import io.github.edwinchang24.salvage.core.util.unaryPlus

@Composable
fun TagEditingRoute(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TagEditingScreenViewModel = hiltViewModel()
) = TagEditingScreen(
    editingTag = viewModel.existingTagId.collectAsStateWithLifecycle().value != null,
    name = GetterSetter(viewModel.name.collectAsStateWithLifecycle().value, viewModel::onEditName),
    color = GetterSetter(viewModel.color.collectAsStateWithLifecycle().value, viewModel::onEditColor),
    description = GetterSetter(
        viewModel.description.collectAsStateWithLifecycle().value,
        viewModel::onEditDescription
    ),
    onSubmitTag = viewModel::submitTag,
    onFinish = onFinish,
    modifier = modifier
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagEditingScreen(
    editingTag: Boolean,
    name: GetterSetter<String>,
    color: GetterSetter<Int>,
    description: GetterSetter<String>,
    onSubmitTag: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (editingTag) "Edit Tag" else "New Tag") },
                navigationIcon = {
                    IconButton(onClick = onFinish) { Icon(Icons.Default.ArrowBack, contentDescription = "Navigate up") }
                }
            )
        },
        bottomBar = {
            BottomBar(
                doneBtnEnabled = name().isNotEmpty(),
                onDone = {
                    onSubmitTag()
                    onFinish()
                },
                onCancel = onFinish
            )
        }
    ) { contentPadding ->
        val scrollState = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(vertical = 24.dp)
                .verticalScroll(scrollState)
        ) {
            TagPreview(name(), color(), modifier = Modifier.fillMaxWidth())
            NameField(name = name, modifier = Modifier.padding(horizontal = 24.dp))
            ColorPicker(color = color)
            DescriptionField(description = description, modifier = Modifier.padding(horizontal = 24.dp))
        }
    }
}

@Composable
fun TagPreview(name: String, color: Int, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        TagPill(tag = Tag(id = "", name = name.ifEmpty { "Preview" }, color = color, description = null))
    }
}

@Composable
private fun NameField(name: GetterSetter<String>, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = name(),
        onValueChange = name.setValue,
        label = { Text("Name*") },
        supportingText = { Text("*required") },
        isError = name().isEmpty(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun ColorPicker(color: GetterSetter<Int>, modifier: Modifier = Modifier) {
    val state = rememberLazyListState(
        initialFirstVisibleItemIndex = TagColor.entries.indexOfFirst { it.colorInt == color() }
    )
    LazyRow(state = state, horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        item { Spacer(modifier = Modifier.size(12.dp)) }
        items(TagColor.entries.map { it.colorInt }) {
            val selected = color() == it
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(it).adjusted())
                    .clickable { color.setValue(it) }
            ) {
                if (selected) Icon(Icons.Default.Check, contentDescription = null)
            }
        }
        item { Spacer(modifier = Modifier.size(12.dp)) }
    }
}

@Composable
private fun DescriptionField(description: GetterSetter<String>, modifier: Modifier = Modifier) {
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
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun BottomBar(doneBtnEnabled: Boolean, onDone: () -> Unit, onCancel: () -> Unit) {
    BottomAppBar(contentPadding = PaddingValues(horizontal = 24.dp)) {
        TextButton(onClick = onCancel, modifier = Modifier.weight(1f)) { Text("Cancel") }
        Spacer(modifier = Modifier.width(24.dp))
        Button(onClick = { if (doneBtnEnabled) onDone() }, enabled = doneBtnEnabled, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Done, contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Done")
        }
    }
}

@AllPreviews
@Composable
private fun TagEditingScreenPreview() = SalvageTheme {
    TagEditingScreen(
        editingTag = true,
        name = +remember { mutableStateOf("") },
        color = +remember { mutableStateOf(TagColor.RED.colorInt) },
        description = +remember { mutableStateOf("") },
        onSubmitTag = {},
        onFinish = {}
    )
}
