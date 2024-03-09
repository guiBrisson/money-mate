package presentation.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.theme.MoneyMateTheme

@Composable
private fun BaseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    color: Color,
    innerPadding: PaddingValues,
    shape: Shape = MaterialTheme.shapes.small,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(color = color)
            .clickable(onClick = onClick)
            .padding(innerPadding),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        color = MaterialTheme.colors.primary,
        innerPadding = PaddingValues(12.dp),
        content = content,
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        color = MaterialTheme.colors.secondary,
        innerPadding = PaddingValues(12.dp),
        content = content,
    )
}

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        color = MaterialTheme.colors.primary,
        shape = CircleShape,
        innerPadding = PaddingValues(16.dp),
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Preview
@Composable
private fun PreviewButtons() {
    MoneyMateTheme {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            PrimaryButton(modifier = Modifier.fillMaxWidth(), onClick = { }) {
                Text(text = "Sign in", style = MaterialTheme.typography.button)
            }

            SecondaryButton(modifier = Modifier.fillMaxWidth(), onClick = { }) {
                Text(text = "Register", style = MaterialTheme.typography.button)
            }

            FloatingButton(
                onClick = { },
                imageVector = Icons.Default.Add,
                contentDescription = "Add new"
            )
        }
    }
}
