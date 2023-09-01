package ui.navigation

import Screen
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ui.viewmodel.TodoViewModel

class MyRootComponent(
    componentContext: ComponentContext,
    private val activityViewModel: TodoViewModel
) : ComponentContext by componentContext {

    val navigation = StackNavigation<Screen>()
    val childStack = componentContext.childStack(
        source = navigation,
        initialStack = { listOf(Screen.Todo) },
        handleBackButton = true,
        childFactory = ::createChildComponent
    )

    private fun createChildComponent(screen: Screen, componentContext: ComponentContext)
    = when (screen) {
        Screen.Detail -> MyChildComponent(componentContext.childContext("detail"), activityViewModel)
        Screen.Todo -> MyChildComponent(componentContext.childContext("todo"), activityViewModel)
    }


}

class MyChildComponent(
    componentContext: ComponentContext,
    activityViewModel: TodoViewModel
) {
    val viewModel: Value<TodoViewModel> = MutableValue(activityViewModel)
}