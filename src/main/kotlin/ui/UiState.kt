package ui

data class UiState(
    val todoList: List<Todo> = emptyList(),
    val selectIndex: Int? = null
)
