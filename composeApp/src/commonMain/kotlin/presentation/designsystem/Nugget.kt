package presentation.designsystem

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BaseNugget(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    contentColor: Color,
    content: @Composable RowScope.() -> Unit,
) {
    Chip(
        modifier = modifier,
        onClick = onClick,
        content = content,
        colors = ChipDefaults.chipColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = contentColor,
        ),
    )
}

@Composable
fun FilterNugget(
    modifier: Modifier = Modifier,
    label: String,
    selected: String? = null,
    onClick: () -> Unit,
) {
    val color = if (selected != null) {
        MaterialTheme.colors.onSurface
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
    }

    BaseNugget(
        modifier = modifier.height(36.dp).semantics { role = Role.Checkbox },
        onClick = onClick,
        contentColor = color,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(0.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = selected ?: label,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
            )

            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Arrow down",
            )
        }
    }

}
