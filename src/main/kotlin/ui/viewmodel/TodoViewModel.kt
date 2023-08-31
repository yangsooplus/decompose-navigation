package ui.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ui.Todo
import ui.UiState

class TodoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun select(index: Int) {
        _uiState.update { it.copy(selectIndex = index) }
    }

    fun add(todo: Todo) {
        _uiState.update { it.copy(todoList = _uiState.value.todoList + todo) }
    }

    fun changeChecked(checked: Boolean, index: Int) {
        val todo = Todo(name = _uiState.value.todoList[index].name, checked)
        val list = _uiState.value.todoList.toMutableList().apply {
            removeAt(index)
            add(index, todo)
        }
        _uiState.update { it.copy(todoList = list) }
    }

}