import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.screen.DetailScreen
import ui.screen.TodoScreen
import ui.viewmodel.TodoViewModel

@Composable
@Preview
fun App() {
    var screenState by remember { mutableStateOf<Screen>(Screen.Todo) }
    val todoViewModel = TodoViewModel()

    MaterialTheme {
        when (screenState) {
            is Screen.Todo -> TodoScreen(
                viewModel = todoViewModel,
                onItemClick = { screenState = Screen.Detail }
            )

            is Screen.Detail -> DetailScreen(
                viewModel = todoViewModel,
                onBack = { screenState = Screen.Todo }
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
