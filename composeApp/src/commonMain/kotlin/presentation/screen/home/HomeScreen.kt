package presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.designsystem.FloatingButton
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

        FloatingButton(
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp),
            onClick = onNewOperation,
            imageVector = Icons.Default.Add,
            contentDescription = "Add new operation"
        )
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
