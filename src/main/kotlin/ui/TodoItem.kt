package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.then(modifier)
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = todo.name, modifier = Modifier.weight(1f))
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = onCheckedChange
        )
    }
}