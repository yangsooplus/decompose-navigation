package ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.Todo
import ui.TodoItem
import ui.viewmodel.TodoViewModel

@Composable
fun TodoScreen(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.then(modifier).fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = text,
                    onValueChange = { text = it }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        viewModel.add(Todo(name = text.ifEmpty { uiState.todoList.size.toString() }, isDone = false))
                        text = ""
                    }
                ) {
                    Text(text = "+")
                }
            }
            LazyColumn {
                items(uiState.todoList.size) { idx ->
                    TodoItem(
                        todo = uiState.todoList[idx],
                        onCheckedChange = { checked ->
                            viewModel.changeChecked(checked = checked, index = idx)
                        },
                        onClick = {
                            viewModel.select(idx)
                            onItemClick()
                        }
                    )
                }
            }
        }
    }

}