import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import ui.navigation.MyRootComponent
import ui.screen.DetailScreen
import ui.screen.TodoScreen
import ui.viewmodel.TodoViewModel

@Composable
@Preview
fun App(rootComponent: MyRootComponent) {
    Children(
        stack = rootComponent.childStack
    ) {
        when (it.configuration) {
            Screen.Detail -> {
                DetailScreen(
                    component = it.instance,
                    onBack = { rootComponent.navigation.pop() }
                )
            }

            Screen.Todo -> {
                TodoScreen(
                    component = it.instance,
                    onItemClick = { rootComponent.navigation.push(Screen.Detail) }
                )
            }
        }
    }
}

fun main() = application {
    val root = MyRootComponent(
        componentContext = DefaultComponentContext(lifecycle = LifecycleRegistry()),
        activityViewModel = TodoViewModel()
    )
    Window(onCloseRequest = ::exitApplication) {
        App(root)
    }
}
