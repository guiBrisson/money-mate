package presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

@Composable
fun MoneyMateTheme(content: @Composable () -> Unit) {
    val darkColorScheme = darkColors(
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
    )

    MaterialTheme(
        typography = getTypography(),
        colors = darkColorScheme,
        content = content,
    )
}
