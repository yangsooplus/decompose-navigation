package ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ui.viewmodel.TodoViewModel

@Composable
fun DetailScreen(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.then(modifier).fillMaxSize(),
        topBar = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    ) { innerPadding ->
        uiState.selectIndex?.let { idx ->
            Text(
                modifier = Modifier.padding(innerPadding),
                text = uiState.todoList[idx].name,
                fontSize = 20.sp
            )
        }

    }
}