package presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.theme.MoneyMateTheme

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onNewOperation: () -> Unit,
) {
    val viewModel = koinViewModel(HomeViewModel::class)
    
    HomeScreen(modifier = modifier)
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "HOME")
    }
}

@Composable
@Preview
private fun HomeScreenPreview() {
    MoneyMateTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            HomeScreen()
        }
    }    
}
