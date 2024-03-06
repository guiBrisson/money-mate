package presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.theme.MoneyMateTheme

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onNewOperation: () -> Unit,
) {
    val viewModel = koinViewModel(HomeViewModel::class)

    HomeScreen(modifier = modifier, onNewOperation)
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    onNewOperation: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxSize()) {
            Text(text = "HOME")
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .clip(CircleShape)
                .clickable { onNewOperation() }
                .background(MaterialTheme.colors.primary)
                .padding(16.dp),
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add new operation",
                tint = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
@Preview
private fun PreviewHomeScreen() {
    MoneyMateTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            HomeScreen(onNewOperation = { })
        }
    }
}
