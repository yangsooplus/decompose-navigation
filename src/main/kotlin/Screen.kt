import com.arkivanov.essenty.parcelable.Parcelable

sealed class Screen: Parcelable {
    object Todo : Screen() {
        private fun readResolve(): Any = Todo
    }

    object Detail : Screen() {
        private fun readResolve(): Any = Detail
    }
}