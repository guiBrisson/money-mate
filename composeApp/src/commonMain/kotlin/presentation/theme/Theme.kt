package presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MoneyMateTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = getTypography(),
        content = content,
    )
}
